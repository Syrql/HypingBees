package fr.syrql.hypingbees.beehives.provider;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.io.provider.IProvider;
import fr.syrql.hypingbees.io.readable.IReadable;
import fr.syrql.hypingbees.io.writeable.IWriteable;
import fr.syrql.hypingbees.utils.files.FileUtils;
import fr.syrql.hypingbees.utils.files.IOUtil;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BeehiveProvider implements IProvider<String, Beehive>, IReadable, IWriteable {

    private final HypingBees hypingBees;
    private Map<String, Beehive> beehiveMap;
    private final File saveDir;

    public BeehiveProvider(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.beehiveMap = new HashMap<>();
        this.saveDir = new File(hypingBees.getDataFolder(), "/datas/");
    }

    /**
     * This function provide id and beehive value
     *
     * @param key - the key to be set
     * @param value - the value to be set
     */

    @Override
    public void provide(String key, Beehive value) {
        this.beehiveMap.put(key, value);
    }

    @Override
    public void remove(String key) {
        Beehive beehive = this.beehiveMap.get(key);


        if (beehive == null) return;

        this.beehiveMap.remove(key);
    }

    /**
     * This getter return beehive value by key
     *
     * @return  key - the value to be set
     */

    @Override
    public Beehive get(String key) {
        return this.beehiveMap.get(key);
    }

    /**
     * This read() fuction will read every json files in datas directory
     * @return Beehive map non-empty.
     */

    @Override
    public void read() {

        File[] files = saveDir.listFiles();
        if (files == null) {
            this.beehiveMap = new HashMap<>();
            return;
        }

        IOUtil ioUtil = hypingBees.getIoUtil();
        for (File file : files) {
            if (file.isFile()) {
                final String json = FileUtils.loadContent(file);
                final Beehive beehive = ioUtil.deserialize(json);
                this.beehiveMap.put(beehive.getIslandUUID() + "-" + beehive.getId(), beehive);
            }
        }
    }

    /**
     * This write() fuction will write every beehive in json files and put in datas directory
     * @return Add to files directory
     */

    @Override
    public void write() {

        final IOUtil ioUtil = this.hypingBees.getIoUtil();

        File[] files = saveDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    try {
                        FileUtils.deleteFile(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        if (this.getBeehives() == null) return;

        for (Beehive beehive : this.getBeehives()) {
            final File file = new File(saveDir, beehive.getIslandUUID() + "-" + beehive.getId() + ".json");
            final String json = ioUtil.serialize(beehive);
            FileUtils.save(file, json);
        }
    }

    /**
     * This Collection retourne Beehive's values
     * @return Beehive map
     */

    public Collection<Beehive> getBeehives() {
        return this.beehiveMap.values();
    }
}
