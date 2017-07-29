/*
*  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.carbon.connector;

import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Sample integration test
 */
public class workableIntegrationTest extends ConnectorIntegrationTestBase {
    private Map<String, String> esbRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        init("workable-connector-1.0.0");
        esbRequestHeadersMap.put("Accept-Charset", "UTF-8");
        esbRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.put("Authorization", "Bearer " + connectorProperties.getProperty("accessToken"));
        apiRequestHeadersMap.put("Content-Type", "application/json");
        System.out.println("-------XXX setEnvironment------");
    }

    /**
     * Positive test case for GetAccounts method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getAccounts integration test with mandatory parameters ")
    public void testGetAccountsWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getAccounts");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getAccounts_mandatory.json");
        System.out.println("\n\n\n\n-------esbRestResponse--------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("apiUrl") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/accounts/";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n--------apiRestResponse-------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("accounts").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("accounts").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("accounts").getJSONObject(0).getString("name"), apiRestResponse.getBody().getJSONArray("accounts").getJSONObject(0).getString("name"));
    }

    /**
     * Positive test case for GetMembers method with mandatory parameters.
     */

    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getMembers integration test with mandatory parameters ")
    public void testGetMembersWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getMembers");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getMembers_mandatory.json");
        System.out.println("\n\n\n\n-------esbRestResponse--------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/members";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---apiRestResponse------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("members").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("members").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("members").getJSONObject(0).getString("name"), apiRestResponse.getBody().getJSONArray("members").getJSONObject(0).getString("name"));
    }

    /**
     * Positive test case for GetMembers method with optional parameters.
     */

    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getMembers integration test with optional parameters ")
    public void testGetMembersWithOptionalParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getMembers");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getMembers_optional.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/members?limit=" + connectorProperties.getProperty("limit") + "&since_id=" + connectorProperties.getProperty("sinceId") + "&max_id=" + connectorProperties.getProperty("maxId") + "&role=" + connectorProperties.getProperty("role") + "&shortcode=" + connectorProperties.getProperty("shortCode");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().toString(), apiRestResponse.getBody().toString());
    }

    /**
     * Positive test case for GetRecruiters method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getRecruiters integration test with mandatory parameters")
    public void testGetRecruitersWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRecruiters");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getRecruiters_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/recruiters";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("recruiters").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("recruiters").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("recruiters").getJSONObject(0).getString("name"), apiRestResponse.getBody().getJSONArray("recruiters").getJSONObject(0).getString("name"));
    }

    /**
     * Positive test case for GetRecruiters method with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getRecruiters integration test with optional parameters")
    public void testGetRecruitersWithOptionalParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getRecruiters");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getRecruiters_optional.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/recruiters?shortcode=" + connectorProperties.getProperty("shortCode");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().toString(), apiRestResponse.getBody().toString());
    }

    /**
     * Positive test case for GetStages method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getStages integration test with mandatory parameters")
    public void testGetStagesWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getStages");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getStages_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/stages";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("slug"), apiRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("slug"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("name"), apiRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("name"));
    }

    /**
     * Positive test case for GetJobs method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobs integration test with mandatory parameters")
    public void testGetJobsWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobs");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobs_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("jobs").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("jobs").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("jobs").getJSONObject(0).getString("title"), apiRestResponse.getBody().getJSONArray("jobs").getJSONObject(0).getString("title"));
    }

    /**
     * Positive test case for GetJobs method with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobs integration test with optional parameters")
    public void testGetJobsWithOptionalParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobs");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobs_optional.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs?state=" + connectorProperties.getProperty("state") + "&limit=" + connectorProperties.getProperty("limit") + "&since_id=" + connectorProperties.getProperty("sinceId") + "&max_id=" + connectorProperties.getProperty("maxId") + "&created_after=" + connectorProperties.getProperty("createdAfter") + "&updated_after=" + connectorProperties.getProperty("updatedAfter") + "&include_fields=" + connectorProperties.getProperty("includeFields");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().toString(), apiRestResponse.getBody().toString());
    }

    /**
     * Positive test case for GetJobByShortCode method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobByShortCode integration test with mandatory parameters")
    public void testGetJobByShortCodeWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCode");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCode_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("shortCode");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getString("id"), apiRestResponse.getBody().getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getString("title"), apiRestResponse.getBody().getString("title"));
    }

    /**
     * Negative test case for getJobByShortCode method.
     */
    @Test(groups = {"wso2.esb"}, description = "Workable {getJobByShortCode} integration test with negative cases.")
    public void testGetJobByShortCodeWithNegativeParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCode");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCode_negative.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("invalidShortCode");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for GetJobByShortCodeActivities method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobByShortCodeActivities integration test with mandatory parameters")
    public void testGetJobByShortCodeActivitiesWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeActivities");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeActivities_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("shortCode") + "/activities";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("activities").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("activities").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("activities").getJSONObject(0).getString("action"), apiRestResponse.getBody().getJSONArray("activities").getJSONObject(0).getString("action"));
    }

    /**
     * Negative test case for getJobByShortCodeActivities method.
     */
    @Test(groups = {"wso2.esb"}, description = "Workable {getJobByShortCodeActivities} integration test with negative cases.")
    public void testGetJobByShortCodeActivitiesWithNegativeParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeActivities");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeActivities_negative.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("invalidShortCode") + "/activities";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for GetJobByShortCodeActivities method with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobByShortCodeActivities integration test with optional parameters")
    public void testGetJobByShortCodeActivitiesWithOptionalParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeActivities");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeActivities_optional.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("shortCode") + "/activities?limit=" + connectorProperties.getProperty("limit") + "&since_id=" + connectorProperties.getProperty("sinceId") + "&max_id=" + connectorProperties.getProperty("maxId");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().toString(), apiRestResponse.getBody().toString());
    }

    /**
     * Positive test case for GetJobByShortCodeApplicationForm method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobByShortCodeApplicationForm integration test with mandatory parameters")
    public void testGetJobByShortCodeApplicationFormWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeApplicationForm");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeApplicationForm_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("shortCode") + "/application_form";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("form_fields").getJSONObject(0).getString("key"), apiRestResponse.getBody().getJSONArray("form_fields").getJSONObject(0).getString("key"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("form_fields").getJSONObject(0).getString("label"), apiRestResponse.getBody().getJSONArray("form_fields").getJSONObject(0).getString("label"));
    }

    /**
     * Negative test case for getJobByShortCodeApplicationForm method.
     */
    @Test(groups = {"wso2.esb"}, description = "Workable {getJobByShortCodeApplicationForm} integration test with negative cases.")
    public void testGetJobByShortCodeApplicationFormWithNegativeParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeApplicationForm");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeApplicationForm_negative.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("invalidShortCode") + "/application_form";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for GetJobByShortCodeQuestions method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobByShortCodeQuestions integration test with mandatory parameters")
    public void testGetJobByShortCodeQuestionsWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeQuestions");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeQuestions_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("shortCode") + "/questions";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("questions").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("questions").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("questions").getJSONObject(0).getString("type"), apiRestResponse.getBody().getJSONArray("questions").getJSONObject(0).getString("type"));
    }

    /**
     * Negative test case for getJobByShortCodeQuestions method.
     */
    @Test(groups = {"wso2.esb"}, description = "Workable {getJobByShortCodeQuestions} integration test with negative cases.")
    public void testGetJobByShortCodeQuestionsWithNegativeParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeQuestions");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeQuestions_negative.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("invalidShortCode") + "/questions";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for GetJobByShortCodeStages method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getJobByShortCodeStages with mandatory parameters")
    public void testGetJobByShortCodeStagesWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeStages");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeStages_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("shortCode") + "/stages";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("slug"), apiRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("slug"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("name"), apiRestResponse.getBody().getJSONArray("stages").getJSONObject(0).getString("name"));
    }

    /**
     * Negative test case for getJobByShortCodeStages method.
     */
    @Test(groups = {"wso2.esb"}, description = "Workable {getJobByShortCodeStages} integration test with negative cases.")
    public void testGetJobByShortCodeStagesWithNegativeParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getJobByShortCodeStages");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getJobByShortCodeStages_negative.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/jobs/" + connectorProperties.getProperty("invalidShortCode") + "/stages";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }

    /**
     * Positive test case for GetCandidates method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getCandidates ")
    public void testGetCandidatesWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getCandidates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getCandidates_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/candidates";
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("candidates").getJSONObject(0).getString("id"), apiRestResponse.getBody().getJSONArray("candidates").getJSONObject(0).getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONArray("candidates").getJSONObject(0).getString("name"), apiRestResponse.getBody().getJSONArray("candidates").getJSONObject(0).getString("name"));
    }

    /**
     * Positive test case for GetCandidates method with optional parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getCandidates with optional parameters ")
    public void testGetCandidatesWithOptionalParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getCandidates");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getCandidates_optional.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/candidates?shortcode=" + connectorProperties.getProperty("shortCode") + "&stage=" + connectorProperties.getProperty("stage") + "&limit=" + connectorProperties.getProperty("limit") + "&since_id=" + connectorProperties.getProperty("sinceId") + "&max_id=" + connectorProperties.getProperty("maxId") + "&created_after=" + connectorProperties.getProperty("createdAfter") + "&updated_after=" + connectorProperties.getProperty("updatedAfter");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().toString(), apiRestResponse.getBody().toString());
    }

    /**
     * Positive test case for GetCandidateById method with mandatory parameters.
     */
    @Test(enabled = true, groups = {"wso2.esb"}, description = "workable test case for getCandidateById with mandatory parameters ")
    public void testGetCandidateByIdWithMandatoryParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getCandidateById");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getCandidateById_mandatory.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/candidates/" + connectorProperties.getProperty("id");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("candidate").getString("id"), apiRestResponse.getBody().getJSONObject("candidate").getString("id"));
        Assert.assertEquals(esbRestResponse.getBody().getJSONObject("candidate").getString("name"), apiRestResponse.getBody().getJSONObject("candidate").getString("name"));
    }

    /**
     * Negative test case for getCandidateById method.
     */
    @Test(groups = {"wso2.esb"}, description = "Workable {getCandidateById} integration test with negative cases.")
    public void testGetCandidateByIdWithNegativeParameters() throws Exception {
        log.info("Successfully tested");
        esbRequestHeadersMap.put("Action", "urn:getCandidateById");
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(proxyUrl, "POST", esbRequestHeadersMap, "esb_getCandidateById_negative.json");
        System.out.println("\n\n\n\n---------------\n" + esbRestResponse.getBody().toString());
        final String apiEndPoint = "https://" + connectorProperties.getProperty("subDomain") + "/spi/" + connectorProperties.getProperty("apiVersion") + "/candidates/" + connectorProperties.getProperty("invalidId");
        System.out.println("\n\n\n\n---------------\n" + apiEndPoint);
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET", apiRequestHeadersMap);
        System.out.println("\n\n\n\n---------------\n" + apiRestResponse.getBody().toString());
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 404);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 404);
    }
}