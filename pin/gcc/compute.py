#!/usr/bin/env python2
import sys

def parse_type(line):
    toks = line.strip().split(" ")
    type_var = toks[0]
    name_var = toks[1]
    if type_var == "enum":
        name_var = toks[2]
    elif type_var == "unsigned":
        type_var = toks[1]
        name_var = toks[2]
    size_var = 0
    if type_var in ["int","bool"]:
        size_var = 4
    elif type_var in ["HOST_WIDE_INT","char*","int*","void*"]:
        size_var = 8
    elif type_var == "enum":
        if "[" in name_var: #hack for gcc - both array variables have 12 bytes
            size_var = 12
        else:
            size_var = 4
    else:
        raise RuntimeError("don't know type: " + type_var)

    return (name_var,type_var,size_var)
    

def main():
    filename = sys.argv[1]
    base_address = int(sys.argv[2],16)
    with open(filename) as infile:
        data = []
        total_size = 0
        for line in infile:
            var_info = parse_type(line);
            total_size = total_size + var_info[2]
            data.append(var_info)
        print total_size
        for d in data:
            print "{} {} {}".format(hex(base_address),d[0],d[1])
            base_address = base_address + d[2]

main()
