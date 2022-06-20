import java.io.*; 
import java.net.*; 
 
public class EchoClient { 
    public static void main(String[] args) throws IOException { 
         
        if (args.length != 2) {  //Se o número de argumentos for diferente de 2
            System.err.println(  //Mensagem de erro informando padrão correto
                "Uso correto: java EchoClient <host name> <port number>"); 
            System.exit(1);      //Saída com erro 1
        } 
 
        String hostName = args[0];     //O host está no primeiro argumento
        int portNumber = Integer.parseInt(args[1]);   //a porta no segundo 
 
        try ( 
            Socket echoSocket = new Socket(hostName, portNumber); //Cria o socket
            PrintWriter out =            //Abertura para envio ao servidor
                new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in =
                new BufferedReader(      //Abertura para o retorno do servidor
                    new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn =
                new BufferedReader(      //Abertura a entrada do teclado
                    new InputStreamReader(System.in)) 
        ) { 
            String userInput;            //Variável auxiliar para entrada
            while ((userInput = stdIn.readLine()) != null) { //Enquanto entrada
                out.println(userInput);  //Apresenta conteúdo digitado
                System.out.println("echo: " + in.readLine()); //Mostra retorno
            }                                                      //do socket
        } catch (UnknownHostException e) {   //Erro de host desconhecido
            System.err.println("Não sei sobre o host " + hostName); 
            System.exit(1);                  //Saída com erro 1
        } catch (IOException e) {            //Erro de I/O com o host informado
            System.err.println("Não consegui I/O para a conexão com " +
                 hostName);
            System.exit(1);                  //Saída com erro 1 
        } //Fim do try..catch
    } //Fim do main()
} //Fim da classe EchoClient