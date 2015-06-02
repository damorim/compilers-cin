package instr.agent;

import instr.agent.InstrumentationState.Event.Kind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InstrumentationState {
  
  static boolean useTraceForThreads = false;
  
  /***
   * will use one or the other data-structure depending
   * on the configuration (HeuristicOptions.traceForThreads)
   ***/
  public static Map<Thread, List<Event>> traceForThreads = new HashMap<Thread,List<Event>>();
  public static List<Event> simpleTrace = new ArrayList<Event>();
  public static Thread t;
  
  public static void clear() {
    if (useTraceForThreads) {
      traceForThreads.clear();
    } else {
      simpleTrace.clear();
    }
  }
  
  public static void dump() {
    for (Event ev : simpleTrace) {
      System.out.println(ev);
    }
  }
  
  private static void updateEvent(Event ev) {
    List<Event> list;
    if (useTraceForThreads) {
      Thread t = Thread.currentThread();
      if ((list = traceForThreads.get(t)) == null) {
        list = new ArrayList<Event>();
        traceForThreads.put(t, list);
      }
    } else {
      list = simpleTrace;
    }
    list.add(ev);
  }
  

  /*** methods called from instrumented sequence ***/
  public synchronized static void entry(String m) {
    Event ev = getEvent(m, Event.Kind.ENTRY);
    updateEvent(ev);
  }
  
  public synchronized static void exit(String m) {
    Event ev = getEvent(m, Event.Kind.EXIT);
    updateEvent(ev);
  }
  
  public synchronized static void throwE(String m) {
    Event ev = getEvent(m, Event.Kind.THROW);
    updateEvent(ev);
  }
  
  public synchronized static void catchE(String m) {
    Event ev = getEvent(m, Event.Kind.CATCH);
    updateEvent(ev);
  }
  
  private static Event getEvent(String m, Kind entry) {
    String key = m + entry.toString();
    Event ev = eventCache.get(key);
    if(ev == null) { //store in the cache
      ev = new Event(m, entry);
      eventCache.put(key, ev);
    }    
    return ev;
  }
  
  /*** supporting types (immutable) ***/
  public static class Event {
    public final String m;
    public final Kind k;
    public enum Kind { THROW , CATCH, ENTRY, EXIT }
    public Event(String m, Kind k) {
      this.m = m;
      this.k = k;
    };
    public String toString() {
      return m + " " + k;
    }
    public static Event parseEvent(String str) {
      Kind kind; 
      int idx;
      if ((idx = str.indexOf(""+Kind.THROW)) != -1) {
        kind = Kind.THROW;
      } else if ((idx = str.indexOf(""+Kind.CATCH)) != -1) {
        kind = Kind.CATCH;
      } else if ((idx = str.indexOf(""+Kind.ENTRY)) != -1) {
        kind = Kind.ENTRY;
      } else if ((idx = str.indexOf(""+Kind.EXIT)) != -1) {
        kind = Kind.EXIT;
      } else {
        throw new RuntimeException();
      }
      return new Event(str.substring(0, idx).trim(), kind);
    }
    public int hashCode() {
      return (m + k.toString()).hashCode();
    }
  }

  public static Map<String, Event> eventCache = new HashMap<String, Event>();
}
