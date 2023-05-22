<body lang="en-GB" link="#000080" vlink="#800000" dir="ltr"><p style="line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt"><b>Travelodge </b>– <span style="font-weight: normal">the
biggest low-cost hotel chain in UK. Unfortunately they don’t
provide official access to their API. My project is an attempt to
create a complete API </span><span style="font-weight: normal">for
this brand</span><span style="font-weight: normal">.</span></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><i><span style="font-weight: normal">What
was my</span> <span style="font-weight: normal">motivation?</span></i></font></p>
<p style="font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt">The main motivation was to
improve my programming skills and learn something new.</font></p>
<p style="font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt"><i>What my application does?</i></font></p>
<p style="font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt">My app can create a list of
London’s hotels and fetch fares for each of then up to 354 days
ahead. The app also store old prices for analytic purposes such as
when is the best time to book a room.</font></p>
<p style="font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt"><i>Challenges I faced.</i></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><span style="font-variant: normal"><span style="font-style: normal"><span style="font-weight: normal">Due
to high volume of requests during fetching all prices for all hotels,
server error 500 was returned. To solve the problem for each request
random browser id is used.</span></span></span></font></p>
<p style="font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt"><i>Features I want to
implement in the future.</i></font></p>
<p style="font-style: normal; font-weight: normal; line-height: 100%; margin-bottom: 0cm">
<font size="4" style="font-size: 14pt">At the moment my app covers
search stage only. I’d like to add book, manage and post booking
features.</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 16pt"><b>API
documentation.</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><span style="font-weight: normal">All
API responses are <a href="http://en.wikipedia.org/wiki/JSON">JSON</a>.
 </span></font><font size="4" style="font-size: 14pt"><span style="font-weight: normal">R</span></font><font size="4" style="font-size: 14pt"><span style="font-weight: normal">esponses
</span></font><font size="4" style="font-size: 14pt"><span style="font-weight: normal">larger
then 4KB </span></font><font size="4" style="font-size: 14pt"><span style="font-weight: normal">are
compressed.</span></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b>Methods
description:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b>Hotels:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels
- GET all hotels details (Greater London area at the moment)</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/{id}
- GET hotel details identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/name/{name}
- GET all hotels with “name” in title</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/{id}
– DELETE hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/updateAll
– update hotels list and and their details from supplier’s
website </font>
</p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b>Current
prices:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/{id}/allPrices
- GET all fares for hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/{id}/prices?from={yyyy-mm-dd}&amp;to={yyyy-mm-dd}
GET fares between dates for hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/{id}/updatePrices
- update prices from supplier’s website for hotel identified by id</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/updatePrices
- update prices from supplier’s website for all hotels</font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><br/>

</p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt"><b>Old
prices:</b></font></p>
<p style="line-height: 100%; margin-bottom: 0cm"><font size="4" style="font-size: 14pt">/hotels/{hotel_id}/priceHistory/{yyyy-mm-dd}
- GET old fares for the date </font>
</p>
</body>
</html>
