package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class HowDivineMainForm extends JFrame {
    private JPanel mainPanel;
    private JPanel mod1Panel;
    private JPanel mod2Panel;
    private JPanel mod3Panel;
    private JPanel targetPanel;
    private JPanel calcPanel;
    private JLabel mod1;
    private JTextField min1Lower;
    private JTextField min1Upper;
    private JLabel mod2;
    private JTextField min2Lower;
    private JTextField min2Upper;
    private JLabel mod3;
    private JTextField min3Lower;
    private JTextField min3Upper;
    private JLabel targetSpinnerLabel;
    private JSpinner targetSpinner;
    private JButton calcButton;
    private JTextField calcOutput;

    public HowDivineMainForm( String title ) {
        super( title );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setContentPane( mainPanel );
        this.pack();

        calcButton.addActionListener( e -> {
            int lower1 = Integer.parseInt( min1Lower.getText().trim() );
            int upper1 = Integer.parseInt( min1Upper.getText().trim() );
            int lower2 = Integer.parseInt( min2Lower.getText().trim() );
            int upper2 = Integer.parseInt( min2Upper.getText().trim() );
            int lower3 = Integer.parseInt( min3Lower.getText().trim() );
            int upper3 = Integer.parseInt( min3Upper.getText().trim() );
            int target = (int)targetSpinner.getValue();

            RollHandler rollHandler = new RollHandler();
            ProbabilityCalculator calculator = new ProbabilityCalculator();

            rollHandler.addModifier( "fire", new Modifier() );
            rollHandler.getModifier( "fire" ).addRange( "range", lower1, upper1 );

            rollHandler.addModifier( "cold", new Modifier() );
            rollHandler.getModifier( "cold" ).addRange( "range", lower2, upper2 );

            rollHandler.addModifier( "light", new Modifier() );
            rollHandler.getModifier( "light" ).addRange( "range", lower3, upper3 );

            double result = calculator.rollAtLeast( target, rollHandler );

            NumberFormat outputFormat = DecimalFormat.getPercentInstance();
            outputFormat.setMinimumFractionDigits( 2 );
            String output = outputFormat.format( result );

            calcOutput.setText( output );
        } );
    }

    public static void main( String[] args ) {
        JFrame frame = new HowDivineMainForm( "How Divine! - A Small PoE Tool" );
        frame.setVisible( true );
    }
}
