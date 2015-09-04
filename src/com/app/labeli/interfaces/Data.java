package com.app.labeli.interfaces;

import java.util.Date;

/**
 * > @DataWithDate
 *
 * Interface for Data with 
 *
 * @author Florian "Aamu Lumi" Kauder
 * for the project @Label[i]
 */
public interface Data {

	public Date getCreated();
	public void setCreated(Date created);
	public Date getLastEdited();
	public void setLastEdited(Date lastEdited);
	
}
