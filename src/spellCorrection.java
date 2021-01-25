
class spellCorrection {

	int editDistance(String word1, String word2) {
		int m=word1.length(), n=word2.length();
		int[][] dist = new int[m+1][n+1];
		
		for(int i=0; i<=m; i++) {
			for(int j=0; j<=n; j++) {
				if(i==0) {
					dist[i][j] = j;
				}
				else if(j==0) {
					dist[i][j] = i;
				}
				else if(word1.charAt(i-1) == word2.charAt(j-1)) {
					dist[i][j] = dist[i-1][j-1];
				}
				else if(word1.charAt(i-1) != word2.charAt(j-1)) {
					dist[i][j] = 1 + Math.min(Math.min(dist[i-1][j], dist[i][j-1]), dist[i-1][j-1]);
				}				
			}
		}
		
		return dist[m][n];
	}
}
