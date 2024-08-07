package com.gayasystem.games.dnd;

import com.gayasystem.games.dnd.common.Thing;
import com.gayasystem.games.dnd.common.coordinates.MeasurementConvertor;
import com.gayasystem.games.dnd.common.coordinates.Orientation;
import com.gayasystem.games.dnd.drawables.Drawer;
import com.gayasystem.games.dnd.ecosystem.beasts.Almiraj;
import com.gayasystem.games.dnd.ecosystem.food.Carrot;
import com.gayasystem.games.dnd.world.Coordinate;
import com.gayasystem.games.dnd.world.World;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.awt.Color.black;
import static javax.swing.GroupLayout.Alignment.CENTER;

@SpringBootApplication
public class Application extends JFrame {
    private final ApplicationContext ctx;
    private final MeasurementConvertor convertor;

    public Application(ApplicationContext ctx, Drawer drawer, World world) throws Exception {
        this.ctx = ctx;
        this.convertor = ctx.getBean(MeasurementConvertor.class);
        init(world);
        var canvas = new Canvas(400, drawer, world);
        canvas.setSize(300, 300);
        canvas.setBackground(black);

        var quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        createLayout(canvas, quitButton);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        var ctx = new SpringApplicationBuilder(Application.class)
                .headless(false).web(WebApplicationType.NONE).run(args);
        EventQueue.invokeLater(() -> {
            var ex = ctx.getBean(Application.class);
            ex.setVisible(true);
        });
    }

    private Thing newThing(Class<? extends Thing> clazz) {
        return ctx.getBean(clazz);
    }

    private void init(World world) {
        var orientation = new Orientation(0);
        world.add(newThing(Almiraj.class), new Coordinate(convertor.feet2Inches(-100), 0), orientation);
        world.add(newThing(Carrot.class), new Coordinate(convertor.feet2Inches(120), 0), orientation);
    }

    private void createLayout(Component... arg) {
        var pane = getContentPane();
        var gl = new GroupLayout(pane);
        pane.setLayout(gl);

        gl.setAutoCreateContainerGaps(true);

        var hGroup = gl.createSequentialGroup();
        hGroup.addGroup(gl.createParallelGroup()
                .addComponent(arg[0])
                .addComponent(arg[1], CENTER));
        gl.setHorizontalGroup(hGroup);

        var vGroup = gl.createSequentialGroup();
        vGroup.addGroup(gl.createParallelGroup()
                .addComponent(arg[0]));
        vGroup.addGroup(gl.createParallelGroup()
                .addComponent(arg[1]));
        gl.setVerticalGroup(vGroup);
    }
}