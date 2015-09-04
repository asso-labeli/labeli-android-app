package com.app.labeli.interfaces;

/**
 * > @DataWithPicture
 *
 * Interface for a data with a picture
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public interface DataWithPicture extends Data{
	
	public String getPictureURL();
	public void setPictureURL(String url);

}
