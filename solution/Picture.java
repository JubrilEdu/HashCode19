/**
 * Created by JB on 2/28/19.
 */
public class Picture {
     private String pictureId;
        private String pictureOrientation;
        private String[] tags;

        public Picture(String pictureId, String pictureOrientation, String[] tags) {
            this.pictureId = pictureId;
            this.pictureOrientation = pictureOrientation;
            this.tags = tags;
        }

        public String getPictureId() {
            return pictureId;
        }

        public void setPictureId(String pictureId) {
            this.pictureId = pictureId;
        }

        public String getPictureOrientation() {
            return pictureOrientation;
        }

        public void setPictureOrientation(String pictureOrientation) {
            this.pictureOrientation = pictureOrientation;
        }

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }
}
