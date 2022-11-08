#include <iostream>
#include <fstream>
#include "Matrix3.h"

int main(int argc, char* argv[]) {
    Matrix3 m1;
    Matrix3 m2;

    for (int i=2; i<argc; i++) {
        std::string file{argv[2]};
        std::ifstream ist{file};
        if (!ist) throw std::runtime_error{"can't ope input file " + file};
    
        std::string s;
        while (std::getline(ist, s)) {
            m2 >> s;
        }
        m1 >> m1+m2;
    }
    std::string file{argv[1]};
    std::ofstream ofs {file};
    if (!ofs) throw std::runtime_error{"can’t open output file " + file};
    ofs << m1 << file << std::endl;
}