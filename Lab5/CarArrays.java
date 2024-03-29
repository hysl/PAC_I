/*
Helen Li
October 11, 2017
*/

import java.util.Scanner;
import java.util.Random;

public class CarArrays
{
	public static void main (String args [])
	{
		Scanner input = new Scanner(System.in);
		boolean [] ignition = new boolean [10];
		char [] carColor = new char [10];
		int [] xPosition = new int [10];
		int [] yPosition = new int [10];
		int currentIndex;
		
		for (int i = 0; i < 10; i++)
		{
			char colorChar = 0;
			carColor[i] = assignColor(colorChar);

			ignition[i] = true;

			int positionH = 0;
			xPosition[i] = randomizePosition(positionH);
			int positionV = 0;
			yPosition[i] = randomizePosition(positionV);
		}

		String action = "";

		while (action.equals(""))
		{
			System.out.println("\nWhich car would you like to use? (Choose from 1-10)");
			System.out.print("Input: ");
			int chooseCar = input.nextInt();
			currentIndex = chooseCar - 1;

			System.out.println("\nWhat would you like to do?\n");
			System.out.println("Turn ignition on/off [1]");
			System.out.println("\t    Move car [2]");
			System.out.println("\tQuit program [Q]\n");
			System.out.print("\tUser input: ");
			action = input.next();

			switch (action)
			{
				case "1":
					ignition[currentIndex] = ignitionSwitch(ignition[currentIndex]);
					reportState(carColor[currentIndex], ignition[currentIndex], xPosition[currentIndex], yPosition[currentIndex], currentIndex);
					action = "";
					break;
				case "2":
					String direction = "";

					while (direction.equals(""))
					{
						System.out.println("\nIn which direction do you want to move the car?");
						System.out.println("H: Horizontal");
						System.out.println("V: Vertical");
						System.out.print("Direction: ");
						direction = input.next();

						switch (direction)
						{
							case "H": case "h":
								System.out.print("\nEnter a movement distance: ");
								int moveH = input.nextInt();
								xPosition[currentIndex] = moveHorizontally(xPosition[currentIndex], moveH, ignition[currentIndex]);
								reportState(carColor[currentIndex], ignition[currentIndex], xPosition[currentIndex], yPosition[currentIndex], currentIndex);
								action = "";
								break;
							case "V": case "v":
								System.out.print("\nEnter a movement distance: ");
								int moveV = input.nextInt();
								yPosition[currentIndex] = moveVertically(yPosition[currentIndex], moveV, ignition[currentIndex]);
								reportState(carColor[currentIndex], ignition[currentIndex], xPosition[currentIndex], yPosition[currentIndex], currentIndex);
								action = "";
								break;
							default:
								System.out.println("\nInvalid Direction! Try again!");
								direction = "";
						}
					}
					break;
				case "Q": case "q":
					System.out.println("\nExiting Car Simulator...");
					reportState(carColor[currentIndex], ignition[currentIndex], xPosition[currentIndex], yPosition[currentIndex], currentIndex);
					System.out.println("\nBye Bye!\n");
					break;
				default:
					System.out.println("\nInvalid Selection! Try again!");
					reportState(carColor[currentIndex], ignition[currentIndex], xPosition[currentIndex], yPosition[currentIndex], currentIndex);
					action = "";
					break;
			}
		}
	}


	public static int randomizePosition (int originalPost)
	{
		originalPost = 1 + (int)(Math.random() * 20);
		return originalPost;
	}


	public static char assignColor (char colorChar)
	{
		int color = 0;
		color = 1 + (int)(Math.random() * 5);

		switch (color)
		{
			case 1:
				colorChar = 'R';
				break;
			case 2:
				colorChar = 'G';
				break;
			case 3:
				colorChar = 'B';
				break;
			case 4:
				colorChar = 'W';
				break;
			case 5:
				colorChar = 'S';
				break;
		}

		return colorChar;
	}


	public static boolean ignitionSwitch (boolean currentIgnition)
	{
		if (currentIgnition == false)
		{
			currentIgnition = true;
		}
		else if (currentIgnition == true)
		{
			currentIgnition = false;
		}
		return currentIgnition;
	}


	public static int moveHorizontally (int xPosition, int moveH, boolean ignition)
	{
		if (ignition == true)
		{
			System.out.println("\nIgnition is OFF! Try again!");
		}
		else
		{
			if (((xPosition + moveH) >= 1) && ((xPosition + moveH) <= 20))
			{
				xPosition = xPosition + moveH;
			}
			else
			{
				System.out.println("\nError! Out of range! Try again!");
			}
		}

		return xPosition;
	}


	public static int moveVertically ( int yPosition, int moveV, boolean ignition)
	{
		if (ignition == true)
		{
			System.out.println("\nIgnition is OFF! Try again!");
		}
		else
		{
			if (((yPosition + moveV) >= 1) && ((yPosition + moveV) <= 20))
			{
				yPosition = yPosition + moveV;
			}
			else
			{
				System.out.println("\nError! Out of range! Try again!");
			}
		}

		return yPosition;
	}


	public static void reportState (char carColor, boolean ignition, int xPosition, int yPosition, int currentIndex)
	{
		System.out.println("\nCar Information:\n");
		System.out.println("   Car #: " + (currentIndex + 1));

		String colorName = "";

		switch (carColor)
		{
			case 'R':
				colorName = "Red";
				break;
			case 'G':
				colorName = "Green";
				break;
			case 'B':
				colorName = "Black";
				break;
			case 'W':
				colorName = "White";
				break;
			case 'S':
				colorName = "Silver";
				break;
		}

		System.out.println("   Color: " + colorName);

		String ignitionState = "";
		if (ignition == false)
		{
			ignitionState = "ON";
		}
		else
		{
			ignitionState = "OFF";
		}

		System.out.println("Ignition: " + ignitionState);

		System.out.println("Location: (" + xPosition + ", " + yPosition + ")\n");

		for (int yCoord = 1; yCoord <= 20; yCoord++)
		{
			for (int xCoord = 1; xCoord <= 20; xCoord++)
			{
				if (xCoord == xPosition && yCoord == yPosition)
				{
					System.out.print(carColor);
				}
				else
				{
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}
}
