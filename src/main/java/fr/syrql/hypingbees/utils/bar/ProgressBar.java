package fr.syrql.hypingbees.utils.bar;

import fr.syrql.hypingbees.configuration.Configuration;
import org.apache.commons.lang.StringUtils;

public class ProgressBar {

    public static String progressBar(Configuration configuration, int value, int maxvalue, int size) {
        float percentage = (float) value / maxvalue;
        int progress = (int) (size * percentage);
        int emptyProgress = size - progress;

        String progressText = StringUtils.repeat(configuration.getProgress(), progress);
        String emptyProgressText = StringUtils.repeat(configuration.getProgressEmpty(), emptyProgress);
        return progressText + emptyProgressText;
    }

}
