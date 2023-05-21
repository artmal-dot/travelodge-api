<body lang="en-GB" link="#000080" vlink="#800000" dir="ltr"><p style="line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt"><b>Hotels:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels - GET all
hotels (Greater London area ATM)</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/{id} - GET
hotel identified by id</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/name/{name}
- GET all hotels with “name” in title</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/{id} –
DELETE hotel identified by id</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/updateAll –
update from supplier’s website hotels list and and their details</p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b>Current
prices:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/{id}/allPrices
- GET all fares for hotel identified by id</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/{id}/prices?from={yyyy-mm-dd}&amp;to={yyyy-mm-dd}
GET fares between dates for hotel identified by id</p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/{id}/updatePrices
- update prices from supplier’s website for hotel identified by id</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/updatePrices
-  update prices from supplier’s website for all hotels</p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm">Old prices:</p>
<p style="line-height: 100%; margin-bottom: 0cm">/hotels/{hotel_id}/priceHistory/{yyyy-mm-dd}
- GET historical fares for the date 
</p>
</body>
