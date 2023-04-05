import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Set;

import javax.imageio.ImageIO;

import org.openqa.selenium.JavascriptExecutor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Bancointer {

	
	WebDriver driver;
	@Before
	public void setUp() throws Exception {
		
		
		
		//PRIMEIRA PARTE 4DVE//
		// codigo para indicar o navegador
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		
		//comando para chamar o driver dentro do meu before
		driver = new ChromeDriver();
		
		//comando para indicar URL		
		driver.get("https://www.4devs.com.br/gerador_de_pessoas");
		String currentWindowHandle = driver.getWindowHandle();
		
		
		
		//comando para maximizar a tela
		driver.manage().window().maximize();
		
		
		
		//comando para interagir o elemento
		Thread.sleep(1000);	
		driver.findElement(By.cssSelector("#bt_gerar_pessoa")).click();
		Thread.sleep(1000);	
		String nome = driver.findElement(By.xpath("//*[@id=\"nome\"]")).getText();
		System.out.println(nome);
		String celular = driver.findElement(By.xpath("//*[@id=\"celular\"]")).getText();
		System.out.println(celular);
		String email = driver.findElement(By.xpath("//*[@id=\"email\"]")).getText();
		System.out.println(email);
		String cpf = driver.findElement(By.xpath("//*[@id=\"cpf\"]")).getText();
		System.out.println(cpf);
		String data_nasc = driver.findElement(By.xpath("//*[@id=\"data_nasc\"]")).getText();
		System.out.println(data_nasc);
		Thread.sleep(1000);	
		
		
		// abrir a segunda URL em uma nova janela
		((JavascriptExecutor) driver).executeScript("window.open()");
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
		    if (!windowHandle.equals(currentWindowHandle)) {
		        driver.switchTo().window(windowHandle);
		        driver.get("https://www.bancointer.com.br/");
		    }
		}
		
		// Execute as ações em uma nova janela
		driver.findElement(By.cssSelector("#gatsby-focus-wrapper > div > header > section > div > div > div > div > div.styles__LogoNIcons-sc-1gbjc3e-6.gjJzHM > div.styles__SearchNFlags-sc-1gbjc3e-8.yVPnY > div.styles__ButtonsWrapper-sc-1gbjc3e-9.PKrxs > button:nth-child(1)")).click();
		Thread.sleep(1000);	
		driver.findElement(By.cssSelector("#onetrust-accept-btn-handler")).click();
		driver.findElement(By.id("nome")).sendKeys(nome);
		driver.findElement(By.id("celular")).sendKeys(celular);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("cpf")).sendKeys(cpf);
		driver.findElement(By.id("dataNascimento")).sendKeys(data_nasc);
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center > div > div:nth-child(2) > form > div > div:nth-child(6) > div > label")).click();
		driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center > div > div:nth-child(2) > form > div > div.col-12.text-center > button")).click();
	
	}
			
	@After
	public void tearDown() throws Exception {
	
		Robot robo = new Robot();
		
		robo.setAutoDelay(1000);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		Rectangle retanguloDoPrint = new Rectangle(
				(int) screenSize.getWidth(),
				(int) screenSize.getHeight()
		);
		
		BufferedImage imagemBuffer = robo.createScreenCapture(retanguloDoPrint);
		
		File arquivoImg = new File ("D:\\tela.png");
		ImageIO.write(imagemBuffer, "png", arquivoImg);
		if(arquivoImg.exists()) {
			System.out.println(arquivoImg.getAbsolutePath());
		}

		driver.quit();
	
		
	}

	@Test
	public void test() throws InterruptedException {
		Thread.sleep(1000);	
		String texto = driver.findElement(By.cssSelector("body > div.style__ModalContent-wuavw4-0.hOXgJK > div.style__Container-sc-138k8xa-0.dlLgSU.d-flex.align-items-center.sent > div > p")).getText();
		System.out.println(texto);
		assertEquals("Prontinho! Recebemos os seus dados.",texto);
		System.out.println("Seu PrintScreen foi tirado com sucesso, ele esta armazenado D:\\ ");
	}

}
