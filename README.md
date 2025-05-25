# eTransferPOC-async
POC to make an Interac e-transfer similar to a E-Commerce Payment model:

->Considers every leg of transfer as a seperate microservice
->Enrolment (Product / transferType check is synchronous)
-> All other checks of transfer are made in Async Fashion

Remove @Async notation in methods to make the calls Synchronous, when needed
