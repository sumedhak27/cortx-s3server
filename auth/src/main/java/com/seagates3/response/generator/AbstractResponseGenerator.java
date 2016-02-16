/*
 * COPYRIGHT 2015 SEAGATE LLC
 *
 * THIS DRAWING/DOCUMENT, ITS SPECIFICATIONS, AND THE DATA CONTAINED
 * HEREIN, ARE THE EXCLUSIVE PROPERTY OF SEAGATE TECHNOLOGY
 * LIMITED, ISSUED IN STRICT CONFIDENCE AND SHALL NOT, WITHOUT
 * THE PRIOR WRITTEN PERMISSION OF SEAGATE TECHNOLOGY LIMITED,
 * BE REPRODUCED, COPIED, OR DISCLOSED TO A THIRD PARTY, OR
 * USED FOR ANY PURPOSE WHATSOEVER, OR STORED IN A RETRIEVAL SYSTEM
 * EXCEPT AS ALLOWED BY THE TERMS OF SEAGATE LICENSES AND AGREEMENTS.
 *
 * YOU SHOULD HAVE RECEIVED A COPY OF SEAGATE'S LICENSE ALONG WITH
 * THIS RELEASE. IF NOT PLEASE CONTACT A SEAGATE REPRESENTATIVE
 * http://www.seagate.com/contact
 *
 * Original author:  Arjun Hariharan <arjun.hariharan@seagate.com>
 * Original creation date: 15-Dec-2015
 */
package com.seagates3.response.generator;

import com.seagates3.response.ServerResponse;
import com.seagates3.response.formatter.xml.XMLResponseFormatter;
import io.netty.handler.codec.http.HttpResponseStatus;

/**
 * Implement all the commonly used response messages in this class.
 */
public abstract class AbstractResponseGenerator {

    public ServerResponse badRequest() {
        String errorMessage = "Bad Request. Check request headers and body.";

        return formatResponse(HttpResponseStatus.BAD_REQUEST,
                "BadRequest", errorMessage);
    }

    public ServerResponse deleteConflict() {
        String errorMessage = "The request was rejected because it attempted "
                + "to delete a resource that has attached subordinate entities. "
                + "The error message describes these entities.";

        return formatResponse(HttpResponseStatus.CONFLICT, "DeleteConflict",
                errorMessage);
    }

    public ServerResponse entityAlreadyExists() {
        String errorMessage = "The request was rejected because it attempted "
                + "to create or update a resource that already exists.";

        return formatResponse(HttpResponseStatus.CONFLICT,
                "EntityAlreadyExists", errorMessage);
    }

    public ServerResponse expiredCredential() {
        String errorMessage = "The request was rejected because the credential "
                + "used to sign the request has expired.";

        return formatResponse(HttpResponseStatus.FORBIDDEN,
                "ExpiredCredential", errorMessage);
    }

    public ServerResponse inactiveAccessKey() {
        String errorMessage = "The access key used to sign the request is inactive.";
        return formatResponse(HttpResponseStatus.FORBIDDEN,
                "InactiveAccessKey", errorMessage);
    }

    public ServerResponse incorrectSignature() {
        String errorMessage = "The request signature we calculated does not "
                + "match the signature you provided. Check your "
                + "Secret Access Key and signing method.";
        return formatResponse(HttpResponseStatus.FORBIDDEN,
                "IncorrectSignature", errorMessage);
    }

    public ServerResponse internalServerError() {
        String errorMessage = "The request processing has failed because of an "
                + "unknown error, exception or failure.";

        return formatResponse(HttpResponseStatus.INTERNAL_SERVER_ERROR,
                "InternalFailure", errorMessage);
    }

    public ServerResponse invalidAction() {
        String errorMessage = "The action or operation requested is "
                + "invalid. Verify that the action is typed correctly.";

        return formatResponse(HttpResponseStatus.BAD_REQUEST, "InvalidAction",
                errorMessage);
    }

    public ServerResponse invalidClientTokenId() {
        String errorMessage = "The X.509 certificate or AWS access key ID "
                + "provided does not exist in our records.";

        return formatResponse(HttpResponseStatus.FORBIDDEN,
                "InvalidClientTokenId", errorMessage);
    }

    public ServerResponse invalidParametervalue() {
        String errorMessage = "An invalid or out-of-range value was "
                + "supplied for the input parameter.";

        return formatResponse(HttpResponseStatus.BAD_REQUEST,
                "InvalidParameterValue", errorMessage);
    }

    public ServerResponse missingParameter() {
        String errorMessage = "A required parameter for the specified action "
                + "is not supplied.";

        return formatResponse(HttpResponseStatus.BAD_REQUEST, "MissingParameter",
                errorMessage);
    }

    public ServerResponse noSuchEntity() {
        String errorMessage = "The request was rejected because it referenced an "
                + "entity that does not exist. ";

        return formatResponse(HttpResponseStatus.NOT_FOUND, "NoSuchEntity",
                errorMessage);
    }

    /**
     * Use this method for internal purpose.
     *
     * @return
     */
    public ServerResponse ok() {
        String errorMessage = "Action successful";
        return new ServerResponse(HttpResponseStatus.OK, errorMessage);
    }

    /**
     * TODO - Identify the return type format i.e XML or JSON and call the
     * respective formatter.
     *
     * @param httpResponseStatus
     * @param responseCode
     * @param responseBody
     * @return
     */
    protected ServerResponse formatResponse(HttpResponseStatus httpResponseStatus,
            String responseCode, String responseBody) {
        return new XMLResponseFormatter().formatErrorResponse(httpResponseStatus,
                responseCode, responseBody);
    }

}
