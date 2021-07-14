package study.data.step38socketserver

import java.io.DataInputStream
import java.io.DataOutputStream
import java.net.ServerSocket

//아래 작업을 다른 IDE(인텔리제이같은)에서 작성후 먼저 실행시키고 socketclient를 실행시킨다.
fun main(){
    //서버 역할을 하기 위한 객체를 생성한다.
    val server = ServerSocket(55555)

    print("사용자 접속대기")

    val socket = server.accept()

    println(socket)
    //클라이언트로 데이터를 보낸다.
    val outputStream = socket.getOutputStream()
    val dos = DataOutputStream(outputStream)
    dos.writeInt(100)
    dos.writeDouble(11.11)
    dos.writeBoolean(true)
    dos.writeUTF("서버가 보낸 문자열")

    //클라이언트가 보낸 데이터를 수신한다.
    val inputStream = socket.getInputStream()
    val dis = DataInputStream(inputStream)

    val a1 = dis.readInt()
    val a2 = dis.readDouble()
    val a3 = dis.readBoolean()
    val a4 = dis.readUTF()

    println("a1 : $a1")
    println("a2 : $a2")
    println("a3 : $a3")
    println("a4 : $a4")

    //서버를 종료한다.
    socket.close()


}