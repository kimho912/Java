#ifndef __MATRIX3_H
#define __MATRIX3_H

class Matrix3 {
  public:
    Matrix3(int m00=0, int m01=0, int m02=0,
            int m10=0, int m11=0, int m12=0,
            int m20=0, int m21=0, int m22=0);
    int get(int x, int y);
    Matrix3 operator+(Matrix3 rhs);
    friend std::ostream& operator<<(std::ostream& out, Matrix3& m);
    friend std::istream& operator>>(std::ostream& in, Matrix3& m);
  private:
    int data[3][3];
};
#endif