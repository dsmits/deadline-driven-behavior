edges = -200:25:200;
histo = histc(sigmoid0to1, edges);
bar(edges, histo);