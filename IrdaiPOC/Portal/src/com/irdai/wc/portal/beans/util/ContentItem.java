package com.irdai.wc.portal.beans.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;

import java.net.HttpURLConnection;
import java.net.URL;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ContentItem implements Serializable {

    private static final long serialVersionUID = 3308316438065918059L;

    public ContentItem() {
        super();
  //      additionalResourceList = new ArrayList<AdditionalResource>();
    }

    private String docId; //dDocID
    private String account; //dDocAccount
    private String owner; //dDocOwner
    private String docType; //dDocType
    private Long fileSize; //dFileSize
    private String extension; //dExtension
    private String webExtension; //dWebExtension
    private String webFileName; //dWebFilename
    private String webURL; 
    private String nativeWebURL; //dWebURL
    private String docFormat; //dFormat
    private String status; //dStatus
    private Long revisionNumber; //dRevisionID
    private String collectionId; //xCollectionID
    private String docInternalName; //dDocName
    private String docTitle; //dDocTitle
    private String truncatedDocTitle; // dDocTitle truncated to 12 chars
    private String docNativeName; //dOriginalName
    private String collectionPath;
    private String securityGroup; //dSecurityGroup
    private Date lastModifiedDate; //dDocLastModifiedDate
    private String lastModifiedDateInGIFormat; // dDocLastModifiedDate formatted to Mon. DD YYYY
    private String website; // xWebsites
    private String comments; //xComments
    private String author; //dDocAuthor
    private Date creationDate; //dCreateDate
    private String creationDateInGIFormat; // dDocLastModifiedDate formatted to Mon. DD YYYY
    private String createdBy; //dDocCreator
    private String lastModifiedBy; //dDocLastModifier
    private String docCategory; //gi_doc_category_temp //TODO
    private final String RENDITION_SPECIFIER = "p";
    private final StringBuffer UCM_CONN_EL =
        new StringBuffer("#{documentsService.defaultConnectionName}");
    private String thumbnailUrl; //for UI purposes
    private String thumbnailId;
    private String filloutOnline; // xgi_fill_online_form
    private String location;
    private String systemNotificationStatus;
    private String additionalResources;
    private Integer additionalResourceListSize;
    private Map<String, String> siteStudioContributionContents;
    private boolean isSiteStudioContentsFetched = false;
    private String websiteObjectType; //WebsiteObjectType

    private String dId;
    private String dRevLabel;
    private String externalUrl;
    private Date expirationDate; //expiration Date
    private Date releaseDate;
    
    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocId() {
        return docId;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocType() {
        return docType;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setWebExtension(String webExtension) {
        this.webExtension = webExtension;
    }

    public String getWebExtension() {
        return webExtension;
    }

    public void setWebFileName(String webFileName) {
        this.webFileName = webFileName;
    }

    public String getWebFileName() {
        return webFileName;
    }

    public void setWebURL(String webURL) {
        this.webURL = webURL;
    }

    public String getWebURL() {       
        if(webURL==null) {
            StringBuffer reqUrl = new StringBuffer("");
            reqUrl.append("/ShowProperty?nodeId=/UCM"+
                           "/" + docInternalName +
                           "//idcPrimaryFile&amp;revision=latestreleased");
            webURL = reqUrl.toString();       
        }
        return webURL;
    }

    public void setDocFormat(String docFormat) {
        this.docFormat = docFormat;
    }

    public String getDocFormat() {
        return docFormat;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRevisionNumber(Long revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Long getRevisionNumber() {
        return revisionNumber;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setDocInternalName(String docInternalName) {
        this.docInternalName = docInternalName;
    }

    public String getDocInternalName() {
        return docInternalName;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocNativeName(String docNativeName) {
        this.docNativeName = docNativeName;
    }

    public String getDocNativeName() {
        return docNativeName;
    }

    public void setCollectionPath(String collectionPath) {
        this.collectionPath = collectionPath;
    }

    public String getCollectionPath() {
        return collectionPath;
    }

    public void setSecurityGroup(String securityGroup) {
        this.securityGroup = securityGroup;
    }

    public String getSecurityGroup() {
        return securityGroup;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsite() {
        return website;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setDocCategory(String docCategory) {
        this.docCategory = docCategory;
    }

    public String getDocCategory() {
        return docCategory;
    }
    
    /**
     * @smishra the format has to be Nov 13, 2013
     * @return
     */
    public String getLastModifiedDateInGIFormat() {
        if (lastModifiedDateInGIFormat == null && lastModifiedDate != null) {
            SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy");
            lastModifiedDateInGIFormat =
                    date.format(lastModifiedDate).toString();
        }
        return lastModifiedDateInGIFormat;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

///ShowProperty?nodeId=/cbi_ucm/#{row.thumbnailId}//idcPrimaryFile&amp;revision=latestreleased
    public String getThumbnailUrl() {
        if (thumbnailUrl == null && thumbnailId.length() > 0) {
            StringBuffer reqUrl = new StringBuffer("");
            reqUrl.append("/ShowProperty?nodeId=/cbi_ucm"+
                           "/" + thumbnailId +
                           "//idcPrimaryFile&amp;revision=latestreleased");
            thumbnailUrl = reqUrl.toString();
        }
        return thumbnailUrl;
    }

    public void setCreationDateInGIFormat(String creationDateInGIFormat) {
        this.creationDateInGIFormat = creationDateInGIFormat;
    }

    public String getCreationDateInGIFormat() {
        if (creationDateInGIFormat == null && creationDate != null) {
            SimpleDateFormat date = new SimpleDateFormat("MMM dd, yyyy");
            creationDateInGIFormat = date.format(creationDate).toString();
        }
        return creationDateInGIFormat;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setSystemNotificationStatus(String systemNotificationStatus) {
        this.systemNotificationStatus = systemNotificationStatus;
    }

    public String getSystemNotificationStatus() {
        return systemNotificationStatus;
    }

    public void setSiteStudioContributionContents(Map<String, String> siteStudioContributionContents) {
        this.siteStudioContributionContents = siteStudioContributionContents;
    }

  
    public void setWebsiteObjectType(String websiteObjectType) {
        this.websiteObjectType = websiteObjectType;
    }

    public String getWebsiteObjectType() {
        return websiteObjectType;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setAdditionalResources(String additionalResources) {
        this.additionalResources = additionalResources;
    }

    public String getAdditionalResources() {
        return additionalResources;
    }

       public void setFilloutOnline(String filloutOnline) {
        this.filloutOnline = filloutOnline;
    }

    public String getFilloutOnline() {
        return filloutOnline;
    }

    public void setTruncatedDocTitle(String truncatedDocTitle) {
        this.truncatedDocTitle = truncatedDocTitle;
    }

    public String getTruncatedDocTitle() {
        if (truncatedDocTitle == null && docTitle != null) {
            truncatedDocTitle =
                    docTitle.length() > 12 ? docTitle.substring(0, 12) :
                    docTitle;
        }
        return truncatedDocTitle;
    }

    public void setDId(String dId) {
        this.dId = dId;
    }

    public String getDId() {
        return dId;
    }

    public void setDRevLabel(String dRevLabel) {
        this.dRevLabel = dRevLabel;
    }

    public String getDRevLabel() {
        return dRevLabel;
    }

    public void setExternalUrl(String externalUrl) {
        this.externalUrl = externalUrl;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public void setAdditionalResourceListSize(Integer additionalResourceListSize) {
        this.additionalResourceListSize = additionalResourceListSize;
    }


    public void setNativeWebURL(String nativeWebURL) {
        this.nativeWebURL = nativeWebURL;
    }

    public String getNativeWebURL() {
        return nativeWebURL;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }
}
