define i32 @half(i32 %0) {
        %x = alloca i32, align 4
        store i32 %0, i32* %x, align 4
        %2 = load i32, i32* %x, align 4
        %3 = sdiv i32 %2, 2
        ret i32 %3
}

define i32 @scalar(i32 %0, i32 %1) {
        %a = alloca i32, align 4
        store i32 %0, i32* %a, align 4
        %b = alloca i32, align 4
        store i32 %1, i32* %b, align 4

        %3 = load i32, i32* %a, align 4
        %4 = load i32, i32* %b, align 4
        %5 = sub i32 0, %4
        %6 = mul i32 %3, %5
        store i32 %6, i32* %a, align 4

        %7 = load i32, i32* %b, align 4
        %8 = add i32 %7, 1
        store i32 %8, i32* %b, align 4

        %9 = load i32, i32* %a, align 4
        %10 = mul i32 %9, %9
        %11 = load i32, i32* %b, align 4
        %12 = add i32 %10, %11
        %13 = sub i32 %11, %9
        %14 = mul i32 %13, %11
        %15 = add i32 %12, %14
        %16 = call i32 @half(i32 %15)
        %17 = add i32 %16, 3
        %18 = sdiv i32 %17, 2
        ret i32 %18
}

define float @fscalar(float %0, float %1) {
        %a = alloca float, align 4
        store float %0, float* %a, align 4
        %b = alloca float, align 4
        store float %1, float* %b, align 4

        %result = alloca float, align 4
        %3 = load float, float* %a, align 4
        %4 = load float, float* %b, align 4
        %5 = fadd float %3, %4
        %6 = fsub float %3, %4
        %7 = fdiv float %5, %6
        %8 = fmul float %3, %4
        %9 = fmul float %7, %8
        %10 = fdiv float %3, %4
        %11 = fdiv float %9, %10
        store float %11, float* %result, align 4
        %12 = load float, float* %result, align 4
        ret float %12
}



define i32 @main() {
        %quantas_trincas = alloca i32, align 4
        store i32 33, i32* %quantas_trincas, align 4
        %valor1 = alloca i32, align 4
        store i32 821, i32* %valor1, align 4
        %tk2 = alloca i32, align 4
        store i32 1, i32* %tk2, align 4
        %tk0 = alloca i32, align 4
        store i32 -1, i32* %tk0, align 4

        %1 = call i32 @scalar(i32 32, i32 821)
        %2 = sub i32 %1, -1
        store i32 %2, i32* %valor1, align 4

        %3 = load i32, i32* %valor1, align 4
        ret i32 %3
}

