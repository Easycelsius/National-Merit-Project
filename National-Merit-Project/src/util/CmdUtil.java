package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class CmdUtil {
	
	public static boolean cmdFlag = true;
	
	public static void cmdStart() {
		
		System.out.println("트리거 권한 부여를 위한 cmd 창입니다.");
		System.out.println("sqlplus Manager/*******");
		System.out.println("GRANT CREATE TRIGGER TO ******;");
		System.out.println("GRANT ALTER ANY TRIGGER TO ******;");
		System.out.println("GRANT DROP ANY TRIGGER TO ******;");
		System.out.println();
		
		ExecutorService executorService = Executors.newCachedThreadPool();

		try {
			
			Process process = Runtime.getRuntime().exec("cmd");
			// Process의 각 stream을 받는다.
			// process의 입력 stream
			OutputStream stdin = process.getOutputStream();
			// process의 에러 stream
			InputStream stderr = process.getErrorStream();
			// process의 출력 stream
			InputStream stdout = process.getInputStream();
			
			// 쓰레드 풀을 이용해서 3개의 stream을 대기시킨다.
			// 출력 stream을 BufferedReader로 받아서 라인 변경이 있을 경우 console 화면에 출력시킨다.
			Executors.newCachedThreadPool().execute(() -> {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(stdout, "euc-kr"))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println(line);
						if(!cmdFlag) {
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			// 에러 stream을 BufferedReader로 받아서 에러가 발생할 경우 console 화면에 출력시킨다.
			Executors.newCachedThreadPool().execute(() -> {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(stderr, "euc-kr"))) {
					String line;
					while ((line = reader.readLine()) != null) {
						System.out.println("err " + line);
						if(!cmdFlag) {
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
			
			// 입력 stream을 BufferedWriter로 받아서 콘솔로부터 받은 입력을 Process 클래스로 실행시킨다.
			Executors.newCachedThreadPool().execute(() -> {
				// Scanner 클래스는 콘솔로 부터 입력을 받기 위한 클래스 입니다.
				try (Scanner scan = new Scanner(System.in)) {
					try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin))) {
						while (cmdFlag) {
							// 콘솔로 부터 엔터가 포함되면 input String 변수로 값이 입력됩니다.
							String input = scan.nextLine();
							// 콘솔에서 \n가 포함되어야 실행된다.
							input += "\n";
							writer.write(input);
							// Process로 명령어 입력
							writer.flush();
							// exit 명령어가 들어올 경우에는 프로그램을 종료합니다.
							if ("exit\n".equals(input)) {
								cmdFlag = false;
								Thread.sleep(100000);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
			executorService.shutdown();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
