package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameSelect implements ActionListener
{
	private boolean cmdStart = false;
	private static final String[] gamelist={
		"Burst",
		"TicTacToe"
	};
	private static final JFrame frame = new JFrame("Game Select");
	public GameSelect(String[] args)
	{
		if(args.length>0)
			getArgs(args);
		if(cmdStart==false)
		{
			JPanel panel = new JPanel();
			
			for(String gn : gamelist)
			{
				JButton newg = new JButton(gn);
				newg.addActionListener(this);
				newg.setActionCommand(gn);
				panel.add(newg);
			}
			
			frame.add(panel);
			frame.pack();
			frame.setVisible(true);
		}
	}
	private void getArgs(String[] args)
	{
		String[] startargs = {"-start", "-play", "-P", "-S", "-p", "-s"};
		System.out.println(java.util.Arrays.deepToString(args));
		if(equalsAny(args, startargs))
		{
			try
			{
				startGame(args[gi(args, startargs)+1]);
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		System.out.println(arg0.getActionCommand());
		startGame(arg0.getActionCommand());
	}
	public void startGame(String gamename)
	{
		try {
			//Try to start game
			Game game = (Game) Class.forName("game.games."+gamename+".StartGame").newInstance();
			System.out.println("Started game successfully: "+game.getGameName());
			frame.dispose();
		} catch (InstantiationException e) {
			System.out.println("Error: could not start game: "+gamename);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Error: game not found: "+gamename);
		}
	}
	private static boolean equalsAny(Object[] a, Object[] b)
	{
		for(int i1=0;i1<a.length;++i1)
			for(int i2=0;i2<b.length;++i2)
				if(a[i1].equals(b[i2]))
					return true;
		return false;
	}
	private static int gi(Object[] a, Object[] b)
	{
		for(int i1=0;i1<a.length;++i1)
			for(int i2=0;i2<b.length;++i2)
				if(a[i1].equals(b[i2]))
					return i2;
		return -1;
	}
}
