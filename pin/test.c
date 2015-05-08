#include <stdio.h>

extern int globalVar1 = 123456;

struct dataStruct {
  int l1;
  int l2;
};

struct dataStruct globalVar2 = {234567,345678};

int main(void) {
  printf("value of g1: %d\n", globalVar1);
  printf("value of g2-1: %d\n", globalVar2.l1);
  printf("value of g2-2: %d\n", globalVar2.l2);
}
