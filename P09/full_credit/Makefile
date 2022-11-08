CXXFLAGS = --std=c++17

all: Matrix3 Matrix3 test_matrix3 interactive_matrix3 read_add_save

# Linkers
Matrix3: Matrix3.o Matrix3.o *.h
	$(CXX) $(CXXFLAGS) Matrix3.o -o Matrix3

test_matrix3: test_matrix3.o Matrix3.o *.h
	$(CXX) $(CXXFLAGS) test_matrix3.o Matrix3.o -o test_matrix3

interactive_matrix3: interactive_matrix3.o Matrix3.o *.h
	$(CXX) $(CXXFLAGS) interactive_matrix3.o Matrix3.o -o interactive_matrix3

read_add_save: read_add_save.o Matrix3 *.h
	$(CXX) $(CXXFLAGS) read_add_save.o Matrix3.o -o read_add_save

# Class compilation
Matrix3.o: Matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c Matrix3.cpp -o Matrix3.o

test_matrix3.o: test_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c test_matrix3.cpp -o test_matrix3.o

interactive_matrix3.o: interactive_matrix3.cpp *.h
	$(CXX) $(CXXFLAGS) -c interactive_matrix3.cpp -o interactive_matrix3.o

read_add_save.o: read_add_save.cpp *.h
	$(CXX) $(CXXFLAGS) -c read_add_save.cpp -o read_add_save.o

Matrix3.o: Matrix3.cpp
	$(CXX) $(CXXFLAGS) -c Matrix3.cpp -o Matrix3.o

clean:
	-rm -f *.o *~ a.out Matrix test_matrix3 interactive_matrix3 read_add_save