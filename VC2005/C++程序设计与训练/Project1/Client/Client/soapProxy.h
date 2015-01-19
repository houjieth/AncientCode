/* soapProxy.h
   Generated by gSOAP 2.7.9b from getQuote.h
   Copyright(C) 2000-2006, Robert van Engelen, Genivia Inc. All Rights Reserved.
   This part of the software is released under one of the following licenses:
   GPL, the gSOAP public license, or Genivia's license for commercial use.
*/

#ifndef soapService_H
#define soapService_H
#include "soapH.h"
class Service
{   public:
	struct soap *soap;
	const char *endpoint;
	Service()
	{ soap = soap_new(); endpoint = "http://localhost:80"; if (soap && !soap->namespaces) { static const struct Namespace namespaces[] = 
{
	{"SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/", "http://www.w3.org/*/soap-envelope", NULL},
	{"SOAP-ENC", "http://schemas.xmlsoap.org/soap/encoding/", "http://www.w3.org/*/soap-encoding", NULL},
	{"xsi", "http://www.w3.org/2001/XMLSchema-instance", "http://www.w3.org/*/XMLSchema-instance", NULL},
	{"xsd", "http://www.w3.org/2001/XMLSchema", "http://www.w3.org/*/XMLSchema", NULL},
	{"SearchService", "http://tempuri.org/SearchService.xsd", NULL, NULL},
	{NULL, NULL, NULL, NULL}
};
	soap->namespaces = namespaces; } };
	virtual ~Service() { if (soap) { soap_destroy(soap); soap_end(soap); soap_free(soap); } };
	virtual int SearchService__Search(std::string query, struct SearchService__Result &result) { return soap ? soap_call_SearchService__Search(soap, endpoint, NULL, query, result) : SOAP_EOM; };
};
#endif
