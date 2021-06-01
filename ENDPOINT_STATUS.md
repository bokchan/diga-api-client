# Status

_**Updated: 01/06/2021**_

Check here to see if the library is known to be working or not against each DiGA endpoint,
together with information on how many companies are using each endpoint, and issue tracking if the endpoint is not working:

| Status                 | Description                                                                                            |
| ---------------------- | ------------------------------------------------------------------------------------------------------ |
| **Prescription codes** |                                                                                                        |
| `CODE TEST OK`         | test codes are working                                                                                 |
| `CODE TEST NOT OK`     | test codes are not working                                                                             |
| `CODE OK`              | real code validation has been attempted and verified to work                                           |
| `CODE NOT VERIFIED`    | real code validation probably works but it has not been tested on a real code yet                      |
| `CODE NOT OK`          | real code validation does not work                                                                     |
| **Billing**            |                                                                                                        |
| `BILLING TEST OK`      | test bill is working (the APIs don't support this completely, but as far as you can get it is working) |
| `BILLING TEST NOT OK`  | test bill is not working                                                                               |
| `BILLING OK`           | billing has been attempted and verified to work                                                        |
| `BILLING NOT VERIFIED` | billing probably works, but it has not been verified with a real bill yet                              |
| `BILLING NOT OK`       | billing does not work                                                                                  |

## Endpoints

| Endpoint                            | #   | Prescription API status             | Billing API status                        |
| ----------------------------------- | --- | ----------------------------------- | ----------------------------------------- |
| diga.bitmarck-daten.de              | 86  | `CODE TEST OK`, `CODE OK`           | `BILLING TEST OK`, `BILLING OK`           |
| diga-api.tk.de/diga/api/public/rest | 1   | `CODE TEST OK`, `CODE OK`           | `BILLING TEST OK`, `BILLING OK`           |
| da-api.gkvi.de                      | 5   | `CODE TEST OK`, `CODE OK`           | `BILLING TEST OK`, `BILLING OK`           |
| diga.kkh.de                         | 1   | `CODE TEST OK`, `CODE NOT VERIFIED` |                                           |
| itscare.da-api.aok.de               | 3   | `CODE TEST OK`, `CODE OK`           | `BILLING TEST OK`, `BILLING NOT VERIFIED` |
| kubus-it.da-api.aok.de              | 2   | `CODE TEST OK`, `CODE NOT VERIFIED` | `BILLING TEST OK`, `BILLING NOT VERIFIED` |
| arge.da-api.aok.de                  | 3   | `CODE TEST NOT OK`, `CODE NOT OK`   | `BILLING TEST OK`, `BILLING NOT VERIFIED` |
| diga.apimisc.de                     | 3   | `CODE TEST OK`, `CODE OK`           | `BILLING TEST NOT OK`, `BILLING OK`       |

## Summary

Endpoints covering 97/103 insurance companies are working with test code validation requests.

Endpoints covering 98/103 insurance companies are working with test invoice requests.

However, since this library is not used in production yet, we can not verify if **real** requests work or not.
We will update this as we find out. If you are using this library in production, and you find out that a request to
one of the un-verified endpoints works, please either send us a message or add a pull request updating this file.
If you find out it doesn't work, [create an Issue](https://github.com/alex-therapeutics/diga-api-client/issues/new/choose)!
