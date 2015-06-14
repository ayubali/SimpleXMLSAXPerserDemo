/**
 * VersionInfo.java
 *
 * Date Jun 14, 2015 4:28:15 PM
 * @author Md Ayub Ali Sarker, ayub.ali.sarker@gmail.com
 * 
 **/
package org.stellar.luncher;

public class VersionInfo {
	private String xmlUpdate = null;
	private String softwareUpdate = null;

	/**
	 * constructor
	 */
	public VersionInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the xmlUpdate
	 */
	public String getXmlUpdate() {
		return xmlUpdate;
	}

	/**
	 * @param xmlUpdate
	 *            the xmlUpdate to set
	 */
	public void setXmlUpdate(String xmlUpdate) {
		this.xmlUpdate = xmlUpdate;
	}

	/**
	 * @return the softwareUpdate
	 */
	public String getSoftwareUpdate() {
		return softwareUpdate;
	}

	/**
	 * @param softwareUpdate
	 *            the softwareUpdate to set
	 */
	public void setSoftwareUpdate(String softwareUpdate) {
		this.softwareUpdate = softwareUpdate;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((softwareUpdate == null) ? 0 : softwareUpdate.hashCode());
		result = prime * result
				+ ((xmlUpdate == null) ? 0 : xmlUpdate.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VersionInfo other = (VersionInfo) obj;
		if (softwareUpdate == null) {
			if (other.softwareUpdate != null)
				return false;
		} else if (!softwareUpdate.equals(other.softwareUpdate))
			return false;
		if (xmlUpdate == null) {
			if (other.xmlUpdate != null)
				return false;
		} else if (!xmlUpdate.equals(other.xmlUpdate))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VersionInfo [xmlUpdate=" + xmlUpdate + ", softwareUpdate="
				+ softwareUpdate + "]";
	}

}
