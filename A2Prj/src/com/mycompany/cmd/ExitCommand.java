package com.mycompany.cmd;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
public class ExitCommand extends Command{
    public ExitCommand()
    {
        super("Exit");
    }
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        Boolean exit = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Ok", "Cancel");
        
        if(exit)
        	Display.getInstance().exitApplication();
    }
}