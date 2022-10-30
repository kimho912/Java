#include <iostream>
#include <fstream>
#include <vector>
#include <string>   
#include <algorithm>

// ./a.out < foo.txt

int main (int argc,  char **argv) {
    std::vector<std::string> v;
    std::string line;

    std::cout << "Before: " << std::endl;
    while (std::getline(std::cin, line) && line.length()) {
        std::cout << line << std::endl;
        v.push_back(line);
    }
    
    //sort in alphabetic order.
    std::sort(v.begin(), v.end());

    std::cout << "\nAfter: " << std::endl;
    for (auto i : v) {
        std::cout << i << std::endl;
    }
}
