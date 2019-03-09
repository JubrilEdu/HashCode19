import java.io.*;
import java.util.*;

/**
 * Created by JB on 2/28/19.
 */
public class PictureMain {
    private ArrayList<Picture> arrayList = new ArrayList<>();
    private ArrayList<Slide> slideArrayList = new ArrayList<>();

    public static void main(String[] args) {
        PictureMain pictureMain = new PictureMain();
        pictureMain.parse("/Users/Jubril/Hashhy/src/a_example.txt");
    }


    public void parse(String filename) {
        int bufferSize = 8 * 1024;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(filename), bufferSize);
            String line = bufferedReader.readLine();
            for (int i = 0; i < Integer.valueOf(line) ; i++) {
                String[] input = bufferedReader.readLine().split(" ");
                String[] tag = Arrays.copyOfRange(input,2,input.length);
                Picture picture = new Picture(String.valueOf(i),input[0],tag);
                arrayList.add(picture);
            }
            generateReport("outputA.txt");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Slide> addPicturesToSlide(ArrayList<Picture> pictures) {

        Picture[] tempPictureArray = new Picture[2];
        int start = 0;
        ArrayList<Slide> slides = new ArrayList<>();
        for (Picture picture : pictures) {
            if (picture.getPictureOrientation().equals("H")) {
                Slide newSlide = new Slide(1, new Picture[]{picture}, new HashSet<>(Arrays.asList(picture.getTags())));
                slides.add(newSlide);
            } else {
                if (start == 0 ) {
                    tempPictureArray[start] = picture;
                    start++;
                } else {
                    tempPictureArray[start] = picture;
                    Slide newSlide = new Slide(2, tempPictureArray, addTagsToSet(tempPictureArray[0].getTags(), tempPictureArray[1].getTags()));
                    slides.add(newSlide);
                    tempPictureArray = new Picture[2];
                    start = 0;
                }
            }

        }
        return slides;
    }

    public Set<String> addTagsToSet(String[] tags1, String[] tag2){
        // change arguement to list then combine
        Set<String> tagSet =  new HashSet<>();
        Collections.addAll(tagSet, tags1);
        Collections.addAll(tagSet, tag2);
        return  tagSet;
    }

    public void generateReport(String reportName) {
        try {
            slideArrayList = swap(addPicturesToSlide(arrayList));
            File fout = new File(reportName);
            FileOutputStream fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(String.valueOf(slideArrayList.size()));
            bw.newLine();
            for (Slide slide: slideArrayList) {
                for (Picture picture: slide.getPictures()) {
                    bw.write(String.valueOf(picture.getPictureId())+" ");
                }
                bw.newLine();
            }

            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Slide> swap(ArrayList<Slide> slides) {
        int initialDifference = 0;
        for (int j = 0; j < slides.size()-1 ; j++) {
            for (int i = 0; i < slides.size()-1; i++) {
                if (compareTwoTagsIfTheyHaveAnyTagsInCommon(slides.get(i).getTags(),slides.get(j).getTags()) > initialDifference) {
                        initialDifference = compareTwoTagsIfTheyHaveAnyTagsInCommon(slides.get(i).getTags(),slides.get(j).getTags());
                    Slide temp = slides.get(j++);
                    slides.add(j++,slides.get(i));
                    slides.add(i,temp);
                }
            }
        }
        return slides;
    }

    public int compareTwoTagsIfTheyHaveAnyTagsInCommon(Set<String> tagMain, Set<String> tag2) {
        Set<String> tempSet = new HashSet<>(tagMain);
        int first = tagMain.size();
        int second = tag2.size();
        tempSet.retainAll(tag2);
        int firstMin = first - tempSet.size();
        int secondMin = second - firstMin;
        return Math.min(Math.min(firstMin,secondMin),tempSet.size());
    }

}

