package org.viodo.toshell.ui.handler;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

/**
 * @author chenxc
 */
public class DummyTransferHandler extends TransferHandler {

    @Override
    protected Transferable createTransferable(JComponent c) {

        if (c instanceof JTree && ((JTree) c).isSelectionEmpty()) {
            return null;
        }

        return new StringSelection("dummy");
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        String message = String.valueOf(support.getDropLocation());
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message, "Drop", JOptionPane.PLAIN_MESSAGE);
        });
        return false;
    }
}
