// See https://developers.google.com/protocol-buffers/docs/cpptutorial

#include <iostream>
#include <fstream>
#include <string>
#include <memory>

#include "boost/asio.hpp"
#include "boost/thread/thread.hpp"

#include "google/protobuf/io/coded_stream.h"
#include "google/protobuf/io/zero_copy_stream_impl.h"

#include "addressbook.pb.h"

using namespace std;

using boost::asio::ip::tcp;

const int max_length = 1024;

typedef std::shared_ptr<tcp::socket> socket_ptr;

void tcpsession(socket_ptr sock) {
  try {
    cout << "Connection established: " << endl;
    // Create data to be sent to client
    tutorial::Person john;
    john.set_id(1234);
    john.set_name("John Doe");
    john.set_email("jdoe@example.com");
    {
      tutorial::Person::PhoneNumber *phone_number = john.add_phone();
      phone_number->set_number("555-4321");
      phone_number->set_type(tutorial::Person::HOME);
    }

      boost::asio::streambuf b;
      std::ostream os(&b);
      john.SerializeToOstream(&os);

      boost::asio::write(*sock, b);
  }
  catch (std::exception &e) {
    std::cerr << "Exception in thread: " << e.what() << "\n";
  }
}

void start_tcpserver(boost::asio::io_service& io_service, short port)
{
  tcp::acceptor a(io_service, tcp::endpoint(tcp::v4(), port));
  for (;;)
  {
    socket_ptr sock(new tcp::socket(io_service));
    a.accept(*sock);
    boost::thread t(boost::bind(tcpsession, sock));
  }
}

int main() {
  // Verify that the version of the library that we linked against is
  // compatible with the version of the headers we compiled against.
  GOOGLE_PROTOBUF_VERIFY_VERSION;

  // TCP server using Boost ASIO
  boost::asio::io_service io_service;

  start_tcpserver(io_service, 2016);

  // Optional:  Delete all global objects allocated by libprotobuf.
  google::protobuf::ShutdownProtobufLibrary();

  return 0;
}

