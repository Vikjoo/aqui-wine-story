package net.spark.backend.data.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
/**
 * 
 * Type*de*vin!
Pays/région*!
Couleur!
Format!
Appellation*
Propriété*
Millesime*
Classement*
Encepagement*
Apogée*
Nombre*
Temperature*de*service*
Provenance*
Caisse*bois*d'origine*
Type*de*cave*
Lot*de*
Niveau**
Etiquette**
TVA*récupérable*
Cote*du*vin*
Prix*d'achat*primeur*
Prix*de*reserve*
Mise*a*prix*
Notes*des*critiques*
Remarque*
Type*de*stockage*
Emplacement*
Photo*
QR*Code*
Conseil*du*Pro*
 * 
 * @author rgunoo
 *
 */
@Entity
public class ProductWine extends AbstractEntity  {
	@Size(max = 255)
    String typeOfWine;
	String country;
	String region;
	String color;
	String format;
	String designation;
	String propertyOf;
	String millesime;
	String classement;
	String apogee;
	String number;
	String temperature;
	String provenance;
	String originalWoodCrate;
	String TypeOfCellar;
	String lotNumber;
	String level;
	String label;
	String tva;
	String wineRating;
	Double buyingPrice;
	Double reservePrice;
	Double setPrice;
	String critic;
	String comments;
	String typeOfStorage;
	String location;
	String picture;
	String qrCode;
	String professionalAdvice;
	public ProductWine() {
	}
	public String getTypeOfWine() {
		return typeOfWine;
	}
	public void setTypeOfWine(String typeOfWine) {
		this.typeOfWine = typeOfWine;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPropertyOf() {
		return propertyOf;
	}
	public void setPropertyOf(String propertyOf) {
		this.propertyOf = propertyOf;
	}
	public String getMillesime() {
		return millesime;
	}
	public void setMillesime(String millesime) {
		this.millesime = millesime;
	}
	public String getClassement() {
		return classement;
	}
	public void setClassement(String classement) {
		this.classement = classement;
	}
	public String getApogee() {
		return apogee;
	}
	public void setApogee(String apogee) {
		this.apogee = apogee;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getProvenance() {
		return provenance;
	}
	public void setProvenance(String provenance) {
		this.provenance = provenance;
	}
	public String getOriginalWoodCrate() {
		return originalWoodCrate;
	}
	public void setOriginalWoodCrate(String originalWoodCrate) {
		this.originalWoodCrate = originalWoodCrate;
	}
	public String getTypeOfCellar() {
		return TypeOfCellar;
	}
	public void setTypeOfCellar(String typeOfCellar) {
		TypeOfCellar = typeOfCellar;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getTva() {
		return tva;
	}
	public void setTva(String tva) {
		this.tva = tva;
	}
	public String getWineRating() {
		return wineRating;
	}
	public void setWineRating(String wineRating) {
		this.wineRating = wineRating;
	}
	public Double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(Double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public Double getReservePrice() {
		return reservePrice;
	}
	public void setReservePrice(Double reservePrice) {
		this.reservePrice = reservePrice;
	}
	public Double getSetPrice() {
		return setPrice;
	}
	public void setSetPrice(Double setPrice) {
		this.setPrice = setPrice;
	}
	public String getCritic() {
		return critic;
	}
	public void setCritic(String critic) {
		this.critic = critic;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTypeOfStorage() {
		return typeOfStorage;
	}
	public void setTypeOfStorage(String typeOfStorage) {
		this.typeOfStorage = typeOfStorage;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getQrCode() {
		return qrCode;
	}
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	public String getProfessionalAdvice() {
		return professionalAdvice;
	}
	public void setProfessionalAdvice(String professionalAdvice) {
		this.professionalAdvice = professionalAdvice;
	}

	
}
