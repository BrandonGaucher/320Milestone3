class Edge
//helper class for the edges of the graph
{
    int source;
	int dest;
	int weight;
	
    public Edge(int origin, int destination, int weight)
    {
        this.source = origin;
        this.dest = destination;
        this.weight = weight;
    }
}