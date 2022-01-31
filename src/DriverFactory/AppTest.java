package DriverFactory;



import org.testng.annotations.Test;

public class AppTest {
@Test
public void KickStart()
{
	try {
	Driverscript ds = new Driverscript();
	ds.startTest();
	}catch(Throwable t)
	{
		System.out.println(t.getMessage());
	}
}
}


