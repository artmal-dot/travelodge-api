<body lang="en-GB" link="#000080" vlink="#800000" dir="ltr"><p style="line-height: 100%; margin-bottom: 0cm">
<font size="3" style="font-size: 12pt"><span style="font-weight: normal">Travelodge</span></font><font size="3" style="font-size: 12pt">
– </font><font size="3" style="font-size: 12pt"><span style="font-weight: normal">the
biggest low-cost hotel chain in UK. Unfortunately they don’t
provide official access to their API. My project is an attempt to
create a complete API.</span></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><i><span style="font-weight: normal">What
was my</span> <span style="font-weight: normal">motivation?</span></i></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><span style="font-weight: normal">The
main motivation was to improve my programming skills and learn
something new.</span></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><i><span style="font-weight: normal">What
my application does?</span></i></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><span style="font-weight: normal">My
app can create a list of London’s hotels and fetch fares for each
of then up to 354 days ahead. The app can also store old prices for
analytic purposes. </span></font>
</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><i><span style="font-weight: normal">Challenges
I faced.</span></i></font></p>
<p style="font-variant: normal; font-style: normal; font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="3" style="font-size: 12pt">Due to high volume of requests
during fetching all prices for all hotels, server error 500 was
returned. To solve the problem for each request random browser id is
used.</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><i><span style="font-weight: normal">Features
I want to implement in the future.</span></i></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b>API
documentation.</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><span style="font-weight: normal">All
API responses are <a href="http://en.wikipedia.org/wiki/JSON">JSON</a></span></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b><font size="3" style="font-size: 12pt">Methods
description:</font></b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><b>Hotels:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels
- GET all hotels details (Greater London area at the moment)</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/{id}
- GET hotel details identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/name/{name}
- GET all hotels with “name” in title</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/{id}
– DELETE hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/updateAll
– update hotels list and and their details from supplier’s
website </font>
</p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><b>Current
prices:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/{id}/allPrices
- GET all fares for hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/{id}/prices?from={yyyy-mm-dd}&amp;to={yyyy-mm-dd}
GET fares between dates for hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/{id}/updatePrices
- update prices from supplier’s website for hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/updatePrices
- update prices from supplier’s website for all hotels</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt"><b>Old
prices:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="3" style="font-size: 12pt">/hotels/{hotel_id}/priceHistory/{yyyy-mm-dd}
- GET historical fares for the date </font>
</p>
</body>
</html>
