define i32 @half(i32 %0) {
  %2 = sdiv i32 %0, 2
  ret i32 %2
}

define i32 @scalar(i32 %0, i32 %1) {
  %3 = sub nsw i32 0, %1
  %4 = mul nsw i32 %0, %3
  %5 = add nsw i32 %1, 1
  %6 = mul nsw i32 %4, %4
  %7 = add nsw i32 %6, %5
  %8 = sub nsw i32 %5, %4
  %9 = mul nsw i32 %8, %5
  %10 = add nsw i32 %7, %9
  %11 = call i32 @half(i32 %10)
  %12 = add nsw i32 %11, 3
  ret i32 %12
}

define i32 @main() {
  %1 = call i32 @scalar(i32 32, i32 821)
  %2 = sub nsw i32 %1, -1
  ret i32 %2
}

