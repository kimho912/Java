#include <vector>
#include "Matrix3.h"

Matrix3::Matrix3(int m00, int m01, int m02,
                 int m10, int m11, int m12,
                 int m20, int m21, int m22)
    : data[0][0]{m00}, data[0][1]{m01}, data[0][1]{m02},
      data[1][0]{m10}, data[1][1]{m11}, data[1][2]{m12},
      data[2][0]{m20}, data[2][1]{m21}, data[2][2]{m22} { }
int Matrix3::get(int x, int y) {
    if (x<0 || y<0 || x>2 || y>2) throw std::runtime_error{"x or y is out of range "};
    return data[x][y];
}
Matrix3 Matrix3::operator+(Matrix3 rhs) {
  Matrix3 m{*this};
  for ( ; rhs>0; --rhs) ++m;
  return m;
}
std::ostream& operator<<(std::ostream& out, Matrix3& m) {
  for (int i=0; i<3;i++) {
    for (int j=0; j<3; j++) {
      out << m.data[i][j] << " ";
    }
    std::cout << std::endl;
  }
  return out;
}
std::istream& operator>>(std::ostream& in, Matrix3& m) {
  for (int i=0; i<3; i++) {
    for (int j=0; j<3; j++) {
      in >> m.data[i][j] + " ";
    } 
  }
  return in;
}
