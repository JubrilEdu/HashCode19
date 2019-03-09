import java.util.Set;

/**
 * Created by JB on 2/28/19.
 */
public class Slide {
        private int noOfPicturesInSlide;
        private Picture[] pictures;
        private Set<String> tags;

        public Slide(int noOfPicturesInSlide, Picture[] pictures, Set<String> tags) {
            this.noOfPicturesInSlide = noOfPicturesInSlide;
            this.pictures = pictures;
            this.tags = tags;
        }

        public int getNoOfPicturesInSlide() {
            return noOfPicturesInSlide;
        }

        public void setNoOfPicturesInSlide(int noOfPicturesInSlide) {
            this.noOfPicturesInSlide = noOfPicturesInSlide;
        }

        public Picture[] getPictures() {
            return pictures;
        }

        public void setPictures(Picture[] pictures) {
            this.pictures = pictures;
        }

        public Set<String> getTags() {
            return tags;
        }

        public void setTags(Set<String> tags) {
            this.tags = tags;
        }

    }
