CXXFLAGS=-std=c++11 -Wall
LDLIBS=-lprotobuf -lboost_system -lboost_thread

all: personsender1 personsender2

personsender1: personsender1.o addressbook.pb.o
	$(CXX) -o $@ $^ $(LDFLAGS) $(LDLIBS)

