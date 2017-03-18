package gui;

import java.io.IOException;

import entità.Elemento;

public interface Visualizzatore {

	public void caricaPannelloDati(Class<? extends Elemento> c) throws ClassNotFoundException, IOException;
}
