# travelodge-api


Hotels:
/hotels - GET all hotels (Greater London area ATM)
/hotels/{id} - GET hotel identified by id
/hotels/name/{name} - GET all hotels with “name” in title
/hotels/{id} – DELETE hotel identified by id
/hotels/updateAll – update from supplier’s website hotels list and and their details

Current prices:
/hotels/{id}/allPrices - GET all fares for hotel identified by id
/hotels/{id}/prices?from={yyyy-mm-dd}&to={yyyy-mm-dd} GET fares between dates for hotel identified by id

/hotels/{id}/updatePrices - update prices from supplier’s website for hotel identified by id
/hotels/updatePrices -  update prices from supplier’s website for all hotels

Old prices:
/hotels/{hotel_id}/priceHistory/{yyyy-mm-dd} - GET historical fares for the date 
