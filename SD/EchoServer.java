import java.net.*; 
import java.io.*; 
 
public class EchoServer { 
    public static void main(String[] args) throws IOException { 
         
        if (args.length != 1) {     //Se o número de argumentos for diferente de 1
            System.err.println("Uso correto: java EchoServer <port number>"); 
            System.exit(1);  //Mostra padrão correto de uso e realiza saída erro 1
        }          
        int portNumber = Integer.parseInt(args[0]); //O nro da porta é o argumento
      
        try ( 
            ServerSocket serverSocket =             //Cria o novo socket
                new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();   //referencia cliente
            PrintWriter out =                              //para o envio
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(        //para a recepção
                new InputStreamReader(clientSocket.getInputStream()));
        ) { 
            String inputLine;     //Variável auxiliar para receber dados enviados
            while ((inputLine = in.readLine()) != null) {  //Enquanto receber
                out.println(inputLine);          //retorna o que foi recebido
            } 
        } catch (IOException e) { //Em caso de erro de I/O
            System.out.println("Exceção causada ao tentar ouvir na porta " 
                + portNumber + " ou ouvindo pela conexão "); 
            System.out.println(e.getMessage()); 
        } //Fim do try..catch
    } //Fim do main()
} //Fim da classe EchoServer