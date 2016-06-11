WalmartSearchAPI
Following steps are followed while designing walmart product search algorithm

1.)Search for the product depending upton the input string given by the user using walmart search api.

2.)Using the first result product id search for the product recommendations using Walmart's Product Recommendation API. Assumption: If first result product id doesnt find return any recommendations then algorithm would search for 2nd result product till 10th result product id and if still no recommendations are found then return empty result.

3.) After returning recommendations for each recommended product we get the reviews.

4.) We can understand the semantics the reviews by considering review stars and number and difference between upvotes and downvotes.

5.) Depending on maximum number of reviews and maximum number of upvotes -downvotes we can sort the products and return the products to the user with better review stars and good number of upvotes in comparison with downvotes. 6.) Assumptions: If no recommendations of product are available then just return the product list to the user.

Test cases:

Ipod: Recommended products: Ipod RCA Viking Pro 10.1" 2-in-1 Tablet 32GB Quad Core={5=1033.0}, Apple iPad mini 2 16GB WiFi={5=198.0}, RCA 7" Tablet 16GB Quad Core.={5=89.0}, Apple iPod touch 32GB, Assorted Colors={5=69.0}, Beats by Dr. Dre Drenched Solo On-Ear Headphones, Assorted Colors={5=40.0}, Just Dance 2016 (Nintendo Wii)={5=26.0}, Apple iPod nano 16GB, Assorted Colors={5=15.0}, Griffin Survivor Extreme-Duty Case for Apple iPod touch 5G, Blue={5=6.0}, Sylvania 7" Dual Screen Portable DVD Player, SDVD8738={5=5.0}, OtterBox Defender Series Case for Apple iPod touch 5th Generation={5=2.0}

Recommended products: Headphones

(First item id doesn’t give recommendation however 3rd one gives)..

Apple iPod shuffle 4th Generation 2GB MP3 Player - Pink (Certified Refurbished)={5=294.0}, Apple Lightning to USB Cable, 3"={5=138.0}, Ematic 6-in-1 Universal Accessory Kit for iPods/MP3 Players={5=128.0}, Apple 5W USB Power Adapter={5=16.0}, 4XEM Apple Original Earphone with Remote and Mic for iPhone 3GS/4/4S={5=11.0}, Apple Dock Connector to USB Cable={5=5.0}, Apple 1m Lightning to USB Cable={5=2.0}, Apple Cube and Lightning Cable, 3'={2=23.0}

Recommended products: Izod ( No recommendations found so returning products returned by search api).
