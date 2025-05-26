package windows;

import GameObjects.GameObject;
import GameObjects.objects.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * An abstract class representing a custom view that extends the JPanel component.
 * This class provides utilities for rendering graphical elements with logical
 * screen scaling and offsets, supporting responsive design based on a reference resolution.
 */
abstract class View extends JPanel {
    private static final int REF_WIDTH = 1440;
    private static final int REF_HEIGHT = 1024;

    public List<GameObject> gameObjects;

    View() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleClick(e.getPoint());
            }
        });
    }

    /**
     * Calculates and returns the current rendering context of the view, which
     * includes the scale factor and offsets required to adapt the view to a
     * logical reference resolution.
     *
     * The rendering context is based on a fixed reference resolution of
     * 1440x1024 and ensures the content is properly scaled and centered within
     * the available space.
     *
     * @return A {@code RenderContext} containing the scale factor, horizontal offset,
     * and vertical offset for rendering the view.
     */
    RenderContext getRenderContext() {
        double scaleX = getWidth() / (double) REF_WIDTH;
        double scaleY = getHeight() / (double) REF_HEIGHT;
        double scale = Math.min(scaleX, scaleY);
        int xOffset = (int) ((getWidth() - REF_WIDTH * scale) / 2);
        int yOffset = (int) ((getHeight() - REF_HEIGHT * scale) / 2);
        return new RenderContext(scale, xOffset, yOffset);
    }

    /**
     * Converts a screen point, represented in pixel coordinates, to a logical point,
     * applying the scale factor and offsets defined by the rendering context.
     *
     * This method translates the given screen coordinates into logical coordinates
     * relative to a reference resolution, ensuring consistency across different screen sizes.
     *
     * @param screenPoint the point in screen coordinates to be converted to logical coordinates
     * @return a {@code Point} representing the logical coordinates corresponding to the given screen point
     */
    Point screenToLogical(Point screenPoint) {
        RenderContext ctx = getRenderContext();
        int logicalX = (int) ((screenPoint.x - ctx.xOffset()) / ctx.scale());
        int logicalY = (int) ((screenPoint.y - ctx.yOffset()) / ctx.scale());
        return new Point(logicalX, logicalY);
    }

    /**
     * Represents the rendering context used for graphical rendering in a custom view.
     * This context encapsulates the information required for responsive rendering,
     * including the scaling factor and the horizontal and vertical offsets.
     *
     * The rendering context is primarily used to adapt the graphical content of a view
     * to fit within a fixed logical resolution, regardless of the screen's physical size.
     * This ensures that the graphical elements are scaled and positioned correctly.
     *
     * Components:
     * - `scale` represents the scaling factor to transform logical coordinates to screen coordinates.
     * - `xOffset` represents the horizontal offset for centering the content.
     * - `yOffset` represents the vertical offset for centering the content.
     *
     * Instances of this record are immutable and thread-safe.
     */
    record RenderContext(double scale, int xOffset, int yOffset) {}

    /**
     * Handles a click event by determining the logical point of the click
     * and delegating the event to any {@code Button} objects within the current
     * set of {@code GameObject}s.
     *
     * Converts the provided screen coordinate into logical coordinates relative
     * to the rendering context, ensuring consistency across different screen
     * resolutions, and processes the click if applicable.
     *
     * @param clickPoint the point on the screen where the click occurred,
     *                   specified in screen coordinates.
     */
     void handleClick(Point clickPoint) {
        Point logicalPoint = screenToLogical(clickPoint);
        for (GameObject go : gameObjects) {
            if (go instanceof Button button) {
                button.handleClick(logicalPoint);
            }
        }
    }
}
