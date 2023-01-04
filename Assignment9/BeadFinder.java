public class BeadFinder {

    // Int var for size of blob array
    private int blobP;
    // Int var for size of bead array
    private int beadP;
    // Array to store Blobs
    private Queue<Blob> blobQueue;
    // 2 dimensionsal boolean to keep track of which pixels have been visited
    private boolean[][] visited;

    // constructor for bead finder
    public BeadFinder(Picture picture, double tau) {
        blobQueue = new Queue<Blob>();
        visited = new boolean[picture.height()][picture.width()];
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                visited[i][j] = false;
            }
        }
        // setting pointer value
        blobP = 0;

        // add pixels to blob with intensity > tau via dfs
        for (int i = 0; i < picture.height(); i++) {
            for (int j = 0; j < picture.width(); j++) {
                if (visited[i][j] == false && Luminance.intensity(picture.get(j, i)) > tau) {
                    Blob nBlob = new Blob();
                    blobQueue.enqueue(dFS(picture, tau, nBlob, i, j));
                    blobP += 1;
                    visited[i][j] = true;
                }

            }
        }
    }

    // DFS function
    private Blob dFS(Picture picture, double tau, Blob blob, int i, int j) {
        blob.add(j, i);
        visited[i][j] = true;
        if (i < picture.height() - 1) {
            if (!visited[i + 1][j] && Luminance.intensity(picture.get(j, i + 1)) > tau) {
                visited[i + 1][j] = true;
                blob = dFS(picture, tau, blob, i + 1, j);
            }
        }
        if (i > 0) {
            if (!visited[i - 1][j] && Luminance.intensity(picture.get(j, i - 1)) > tau) {
                visited[i - 1][j] = true;
                blob = dFS(picture, tau, blob, i - 1, j);
            }
        }
        if (j < picture.width() - 1) {
            if (!visited[i][j + 1] && Luminance.intensity(picture.get(j + 1, i)) > tau) {
                visited[i][j + 1] = true;
                blob = dFS(picture, tau, blob, i, j + 1);
            }
        }
        if (j > 0) {
            if (!visited[i][j - 1] && Luminance.intensity(picture.get(j - 1, i)) > tau) {
                visited[i][j - 1] = true;
                blob = dFS(picture, tau, blob, i, j - 1);
            }
        }

        return blob;
    }

    // returns beads, which are blobs over the min pixel threshold
    public Blob[] getBeads(int min) {
        beadP = 0;
        Blob[] temp = new Blob[blobP];

        for (int i = 0; i < blobP; i++) {
            Blob current = blobQueue.dequeue();
            if (current.mass() >= min) {
                temp[beadP] = current;
                beadP += 1;
            }
        }

        Blob[] beadList = new Blob[beadP];
        for (int i = 0; i < beadP; i++) {
            beadList[i] = temp[i];

        }
        return beadList;
    }


    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture myPicture = new Picture(args[2]);
        BeadFinder beadFinder = new BeadFinder(myPicture, tau);
        Blob[] beadList = beadFinder.getBeads(min);

        for (int i = 0; i < beadFinder.beadP; i++) {
            StdOut.println(beadList[i]);
        }

    }
}
