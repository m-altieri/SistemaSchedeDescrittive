package gui;

import java.io.IOException;

import entit�.Elemento;

public interface Visualizzatore {

	public void caricaPannelloDati(Class<? extends Elemento> c) throws ClassNotFoundException, IOException;
}
