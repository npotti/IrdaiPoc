package com.irdai.wc.portal.beans.util;

import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import oracle.adf.share.logging.ADFLogger;

import oracle.stellent.ridc.IdcClient;
import oracle.stellent.ridc.IdcClientException;
import oracle.stellent.ridc.IdcClientManager;
import oracle.stellent.ridc.IdcContext;
import oracle.stellent.ridc.model.DataBinder;
import oracle.stellent.ridc.model.DataObject;
import oracle.stellent.ridc.model.DataResultSet;
import oracle.stellent.ridc.protocol.ServiceResponse;

import oracle.webcenter.doclib.model.DocLibUtils;

public class ContentUtils {
    public ContentUtils() {
        super();
        logger.info("ContentUtils Constructor Starts");
        IdcClientManager manager = new IdcClientManager();
        try {
            logger.info("ContentUtils Intialized Manager");
            logger.info("ContentUtils getIdcURL :" + getIDCUrl());
            idcClient = manager.createClient(getIDCUrl());
            logger.info("ContentUtils Intialized idcClient");

            //: Get the connection userContext through Identity Propagation
        //                  userContext = new IdcContext("appadmin");

        } catch (IdcClientException ice) {
            ice.printStackTrace();
            logger.info("ContentUtils IdcClientException Exception" +
                        ice.toString());
        } catch (Exception ice) {
            ice.printStackTrace();
            logger.info("ContentUtils Exception Exception" + ice.toString());
        } 
        logger.info("ContentUtils Constructor Ends");
    }
    
    private static ADFLogger logger = ADFLogger.createADFLogger(ContentUtils.class);
    private static IdcClient idcClient;
    /** CONTENT PROPERTIES **/
    private static final String dID = "dID";
    private static final String dDocID = "dDocID";
    private static final String dDocAccount = "dDocAccount";
    private static final String dDocOwner = "dDocOwner";
    private static final String dDocType = "dDocType";
    private static final String dFileSize = "dFileSize";
    private static final String dExtension = "dExtension";
    private static final String dWebExtension = "dWebExtension";
    private static final String dWebFilename = "dWebFilename";
    private static final String dWebURL = "dWebURL";
    private static final String dFormat = "dFormat";
    private static final String dStatus = "dStatus";
    private static final String dRevisionID = "dRevisionID";
    private static final String xCollectionID = "xCollectionID";
    private static final String dDocName = "dDocName";
    private static final String dDocTitle = "dDocTitle";
    private static final String dOriginalName = "dOriginalName";
    private static final String dDocLastModifiedDate = "dDocLastModifiedDate";
    private static final String xWebsites = "xWebsites";
    private static final String dDocAuthor = "dDocAuthor";
    private static final String dDocCreator = "dDocCreator";
    private static final String dDocLastModifier = "dDocLastModifier";
    private static final String dSecurityGroup = "dSecurityGroup";
    private static final String dCreateDate = "dCreateDate";
    private static final String dLastModifiedDate = "dLastModifiedDate";
    private static final String dParentCollectionID = "dParentCollectionID";
    private static final String xComments = "xComments";
    private static final String dOutDate = "dOutDate";
    private static final String dInDate = "dInDate";
    private static final String dAccount = "dDocAccount";
    
    private static final String IDCSERVICE = "IdcService";
    private static final String hasCollectionPath = "hasCollectionPath";
    private static final String dCollectionPath = "dCollectionPath";
    private static final String dCollectionName = "dCollectionName";
    private static final String dCollectionID = "dCollectionID";
    private static final String TRUE = "true";
    private static final String COLLECTIONS_RESULTSET = "COLLECTIONS";
    private static final String GET_SEARCH_RESULTS = "GET_SEARCH_RESULTS";
    private static final String COLLECTION_CONTENT_SERVICE =
        "COLLECTION_GET_CONTENTS";
    private static final String hasCollectionID = "hasCollectionID";
    private static final String CONTENTS_RESULTSET = "CONTENTS";
    
    private static DocLibUtils utils = new DocLibUtils();
    
    public static String getIDCUrl() {
             return "idc://172.16.30.120:4444";
    }
    
    public List<ContentItem> getContentItemsFromQuery(String queryText,
                                                      String sortField,
                                                      String sortOrder) throws ParseException,
                                                                               IdcClientException, NamingException {
        ServiceResponse serviceResponse = null;
        int totalRows = 0;
        int rowsPerPage = 0;
        int endRow = 0;
        int numOfPages = 0;
        DataBinder dataBinder = null;
        List<ContentItem> contentItems = new ArrayList<ContentItem>();

        try {
            IdcContext userContext = null;
            String connName = utils.getDefaultConnectionName();
            userContext = new IdcContext("weblogic");
            dataBinder = idcClient.createBinder();
            dataBinder.putLocal(IDCSERVICE, GET_SEARCH_RESULTS);
            dataBinder.putLocal("QueryText", queryText);

            //            if (noOfItems != -1) {
            //                dataBinder.putLocal("ResultCount", noOfItems + "");
            //            }

            if (sortField != null && !"".equals(sortField.trim()))
                dataBinder.putLocal("SortField", sortField);

            if (sortField != null && !"".equals(sortField.trim()))
                dataBinder.putLocal("SortOrder", sortOrder);

            serviceResponse = idcClient.sendRequest(userContext, dataBinder);
            DataBinder responseDataBinder =
                serviceResponse.getResponseAsBinder();

            DataResultSet dataResultSet =
                responseDataBinder.getResultSet("SearchResults");
            for (DataObject obj : dataResultSet.getRows()) {
                ContentItem item = getPopulatedContentItem(obj);
                contentItems.add(item);
            }

            totalRows =
                    Integer.parseInt(responseDataBinder.getLocal("TotalRows"));

            if (totalRows > 0) {

                endRow =
                        Integer.parseInt(responseDataBinder.getLocal("EndRow"));
                numOfPages =
                        Integer.parseInt(responseDataBinder.getLocal("NumPages"));

                // fetch the remaining results recursively using the same binder
                if (numOfPages > 1) {

                    rowsPerPage = endRow;
                    int startRow = endRow + 1;
                    int i = 1;

                    while (i <= numOfPages) {

                        if (totalRows >= (startRow + rowsPerPage - 1))
                            endRow = startRow + rowsPerPage - 1;
                        else
                            endRow = totalRows;

                        dataBinder.putLocal("StartRow", startRow + "");
                        dataBinder.putLocal("EndRow", endRow + "");
                        serviceResponse =
                                idcClient.sendRequest(userContext, dataBinder);
                        responseDataBinder =
                                serviceResponse.getResponseAsBinder();
                        dataResultSet =
                                responseDataBinder.getResultSet("SearchResults");
                        for (DataObject obj : dataResultSet.getRows()) {
                            ContentItem item = getPopulatedContentItem(obj);
                            contentItems.add(item);
                        }

                        startRow = endRow + 1;
                        i++;
                    }
                }
            }

        } catch (IdcClientException e) {
            logger.severe("Unable to fetch content items matching the query due to : " +
                          e.getMessage());
            e.printStackTrace();
            throw e;
        } catch (ParseException e) {
            logger.severe("Unable to fetch content items matching the query due to : " +
                          e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            if (serviceResponse != null) {
                serviceResponse.close();
            }
        }

        return contentItems;
    }
    
        private ContentItem getPopulatedContentItem(DataObject obj) throws ParseException {
            ContentItem item = new ContentItem();
            item.setDId(obj.get(dID));
            item.setDocId(obj.get(dDocID));
            item.setAccount(obj.get(dDocAccount));
            item.setOwner(obj.get(dDocOwner));
            item.setDocType(obj.get(dDocType));
            item.setFileSize(obj.get(dFileSize) != null ?
                             new Long(obj.get(dFileSize)) : null);
            item.setExtension(obj.get(dExtension));
            item.setWebExtension(obj.get(dWebExtension));
            item.setWebFileName(obj.get(dWebFilename));
            //item.setWebURL(obj.get(dWebURL));@smishra commented. Its calculated in ContentItem.java
            item.setNativeWebURL(obj.get(dWebURL));
            item.setDocFormat(obj.get(dFormat));
            item.setStatus(obj.get(dStatus));
            item.setRevisionNumber(obj.get(dRevisionID) != null ?
                                   new Long(obj.get(dRevisionID)) : null);
            item.setCollectionId(obj.get(xCollectionID));
            item.setDocInternalName(obj.get(dDocName));
            item.setDocTitle(obj.get(dDocTitle));
            item.setDocNativeName(obj.get(dOriginalName));
            item.setSecurityGroup(obj.get(dSecurityGroup));
            item.setAuthor(obj.get(dDocAuthor));
            item.setCreatedBy(obj.get(dDocCreator));
            item.setLastModifiedBy(obj.get(dDocLastModifier));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (obj.get(dOutDate) != null && !obj.get(dOutDate).equals("null") &&
                !obj.get(dOutDate).equals(""))
                item.setExpirationDate(df.parse(obj.get(dOutDate)));
            if (obj.get(dCreateDate) != null &&
                !obj.get(dCreateDate).equals("null") &&
                !obj.get(dCreateDate).equals(""))
                item.setCreationDate(df.parse(obj.get(dCreateDate)));
            item.setComments(obj.get(xComments));
            if (obj.get(dInDate) != null && !obj.get(dInDate).equals("null") &&
                !obj.get(dInDate).equals(""))
                item.setReleaseDate(df.parse(obj.get(dInDate)));
            return item;
        }
        
//        public static void main(String ar[]){
//            try {
//                results =
//                        getContentItemsFromQuery("dDocName <matches> `" + contentId +
//                                                 "`", null, null);
//                if (results != null && results.size() > 0)
//                    item = results.get(0);
//            }
//            dDocTitle <starts> `nitish`
//        }

}


