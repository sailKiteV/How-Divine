package main;

import javax.swing.*;
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
    private JTextField fireLower1;
    private JTextField fireLower2;
    private JLabel mod2;
    private JTextField coldLower1;
    private JTextField coldLower2;
    private JLabel mod3;
    private JTextField lightLower1;
    private JTextField lightLower2;
    private JLabel targetSpinnerLabel;
    private JSpinner targetSpinner;
    private JButton calcButton;
    private JTextField calcOutput;
    private JTextField fireUpper1;
    private JTextField fireUpper2;
    private JTextField coldUpper1;
    private JTextField coldUpper2;
    private JTextField lightUpper1;
    private JTextField lightUpper2;

    public HowDivineMainForm( String title ) {
        super( title );

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setContentPane( mainPanel );
        this.pack();

        calcButton.addActionListener( e -> {
            int fireLower1 = Integer.parseInt( this.fireLower1.getText().trim() );
            int fireLower2 = Integer.parseInt( this.fireLower2.getText().trim() );
            int fireUpper1 = Integer.parseInt( this.fireUpper1.getText().trim() );
            int fireUpper2 = Integer.parseInt( this.fireUpper2.getText().trim() );

            int coldLower1 = Integer.parseInt( this.coldLower1.getText().trim() );
            int coldLower2 = Integer.parseInt( this.coldLower2.getText().trim() );
            int coldUpper1 = Integer.parseInt( this.coldUpper1.getText().trim() );
            int coldUpper2 = Integer.parseInt( this.coldUpper2.getText().trim() );

            int lightLower1 = Integer.parseInt( this.lightLower1.getText().trim() );
            int lightLower2 = Integer.parseInt( this.lightLower2.getText().trim() );
            int lightUpper1 = Integer.parseInt( this.lightUpper1.getText().trim() );
            int lightUpper2 = Integer.parseInt( this.lightUpper2.getText().trim() );

            int target = (int)targetSpinner.getValue();

            RollHandler rollHandler = new RollHandler();
            ProbabilityCalculator calculator = new ProbabilityCalculator();

            rollHandler.addModifier( "fire", new Modifier() );
            rollHandler.getModifier( "fire" ).addRange( "lower", fireLower1, fireLower2 );
            rollHandler.getModifier( "fire" ).addRange( "upper", fireUpper1, fireUpper2 );

            rollHandler.addModifier( "cold", new Modifier() );
            rollHandler.getModifier( "cold" ).addRange( "lower", coldLower1, coldLower2 );
            rollHandler.getModifier( "cold" ).addRange( "upper", coldUpper1, coldUpper2 );

            rollHandler.addModifier( "light", new Modifier() );
            rollHandler.getModifier( "light" ).addRange( "lower", lightLower1, lightLower2 );
            rollHandler.getModifier( "light" ).addRange( "upper", lightUpper1, lightUpper2 );

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
