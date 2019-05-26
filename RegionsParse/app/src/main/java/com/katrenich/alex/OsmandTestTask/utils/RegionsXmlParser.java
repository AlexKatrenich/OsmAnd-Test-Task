package com.katrenich.alex.OsmandTestTask.utils;

import android.util.Log;

import com.katrenich.alex.OsmandTestTask.entities.Flag;
import com.katrenich.alex.OsmandTestTask.entities.Region;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

public class RegionsXmlParser {
    public static final String TAG = "RegionsXmlParser";
    private static List<Region> mRegions;

    // Setting flags when parameter 'type' specified
    private static void setTypeParameter(String type, Region region){
        switch (type){
            case "map" : {
                region.setHillshade(Flag.NO);
                region.setSrtm(Flag.NO);
                break ;
            }
            case "srtm" : {
                region.setHillshade(Flag.NO);
                region.setMap(Flag.NO);
                region.setRoads(Flag.NO);
                region.setWiki(Flag.NO);
                break;
            }
            case "hillshade" : {
                region.setMap(Flag.NO);
                region.setRoads(Flag.NO);
                region.setWiki(Flag.NO);
                region.setSrtm(Flag.NO);
                break;
            }
            case "continent" : {
                region.setHillshade(Flag.NO);
                region.setMap(Flag.NO);
                region.setRoads(Flag.NO);
                region.setWiki(Flag.NO);
                region.setSrtm(Flag.NO);
                break;
            }
        }

    }

    public static List<Region> parseRegions(XmlPullParser parser){
        mRegions = new ArrayList<>();
        parseRecursive(parser, null, 0);
        return mRegions;
    }


    // recursive method to parse xml-document one depth-range in one call-method
    private static XmlPullParser parseRecursive(XmlPullParser parser, Region parent, int depth){
        Region currentRegion = null;

        try {

            while (parser.getDepth() >= depth){
                if (parser.getEventType() == XmlPullParser.END_DOCUMENT) break;

                switch (parser.getEventType()){
                    case XmlPullParser.START_DOCUMENT: {
                        break;
                    }

                    case XmlPullParser.START_TAG : {

                        if (parser.getName().equals("region")){
                            Region region = new Region();

                            // Get parameter 'type'
                            String type = parser.getAttributeValue(null, "type");
                            if (type != null) {
                                region.setType(type);
                                setTypeParameter(type, region);
                            }

                            // Get parameter 'map'
                            String map = parser.getAttributeValue(null, "map");
                            if (map != null){
                                region.setMap(map.equals("yes") ? Flag.YES : Flag.NO);
                                region.setWiki(map.equals("yes") ? Flag.YES : Flag.NO);
                                region.setRoads(map.equals("yes") ? Flag.YES : Flag.NO);
                            }

                            // Get parameter 'wiki'
                            String wiki = parser.getAttributeValue(null, "wiki");
                            if (wiki != null) {
                                region.setWiki(wiki.equals("yes") ? Flag.YES : Flag.NO);
                            }

                            // Get parameter 'roads'
                            String roads = parser.getAttributeValue(null, "roads");
                            if (roads != null){
                                region.setRoads(roads.equals("yes") ? Flag.YES : Flag.NO);
                            }


                            // Get parameter 'srtm'
                            String srtm = parser.getAttributeValue(null, "srtm");
                            if (srtm != null){
                                region.setSrtm(srtm.equals("yes") ? Flag.YES : Flag.NO);
                            }

                            // Get parameter 'hillshade'
                            String hillshade = parser.getAttributeValue(null, "hillshade");
                            if (hillshade != null){
                                region.setHillshade(hillshade.equals("yes") ? Flag.YES : Flag.NO);
                            }

                            // Get parameter 'join_map_files'
                            String joinMapFiles = parser.getAttributeValue(null, "join_map_files");
                            if (joinMapFiles != null){
                                region.setJoinMapFiles(joinMapFiles.equals("yes") ? Flag.YES : Flag.NO);
                            }

                            // Get parameter 'name'
                            String name = parser.getAttributeValue(null, "name");
                            if (name != null){
                                region.setName(name);
                            } else {
                                throw new Exception("Can`t parse parameter 'name' ");
                            }

                            // Get parameter 'poly_extract'
                            String polyExtract = parser.getAttributeValue(null, "poly_extract");
                            if (polyExtract != null) region.setPolyExtract(polyExtract);

                            // Get parameter 'lang'
                            String lang = parser.getAttributeValue(null, "lang");
                            if (lang != null) region.setLang(lang);

                            // Get parameter 'translate'
                            String translate = parser.getAttributeValue(null, "translate");
                            if (translate != null) region.setTranslate(translate);

                            // Set parent region
                            region.setParentRegion(parent);

                            // Get inner download suffix
                            String innerDownloadSuffix = parser.getAttributeValue(null, "inner_download_suffix");
                            region.setInnerDownLoadSuffix(innerDownloadSuffix);

                            // Get parameter 'boundary'
                            String boundary = parser.getAttributeValue(null, "boundary");
                            if (boundary != null){
                                if(boundary.contains("$")) {
                                    // check for duplicates name in region list
                                    if (nameHasDuplicates(region)){
                                        boundary = region.getParentRegion().getName() + "/" + region.getName();
                                    } else {
                                        boundary = region.getName();
                                    }
                                }
                                region.setBoundary(boundary);
                            }

                            // Get download_name
                            String downloadPrefix = parser.getAttributeValue(null, "download_prefix");
                            String downloadSuffix = parser.getAttributeValue(null, "download_suffix");



                            StringBuffer stringBuffer = new StringBuffer();
                            if (downloadPrefix != null) stringBuffer.append(downloadPrefix + "_");
                            stringBuffer.append(name);
                            if (downloadSuffix != null) {
                                stringBuffer.append("_" + downloadSuffix);
                            } else {
                                String s = getParentInnerDownloadSuffix(region);
                                if (s != null) stringBuffer.append("_" + s);
                            }
                            region.setDownloadName(stringBuffer.toString());

                            currentRegion = region;
                            mRegions.add(region);

                        }
                        break;
                    }

                    case XmlPullParser.END_TAG : {
                        break;
                    }

                    case XmlPullParser.TEXT : {
                        String text = parser.getText();
                        Log.i(TAG, "parseRegionsXML: TAG_TEXT: " + text);
                        break;
                    }

                    default: {
                        break;
                    }
                }

                parser.next();
                if (parser.getDepth() > depth) parser = parseRecursive(parser, currentRegion, parser.getDepth());
            }
        } catch (Exception e){
            Log.e(TAG, "parseRegionsXML: ", e);
        }

        return parser;
    }

    // recursive get inner_download_suffix from parent region
    private static String getParentInnerDownloadSuffix(Region region) {
        Region parent = region.getParentRegion();
        if (parent == null) return null;


        String suffix = region.getParentRegion().getInnerDownLoadSuffix();
        if (suffix == null) {
            return getParentInnerDownloadSuffix(region.getParentRegion());
        } else {
            return suffix;
        }
    }

    private static boolean nameHasDuplicates(Region region){
        for (Region r : mRegions) {
            if(region.getName().equalsIgnoreCase(r.getName())) return true;
        }
        return false;
    }

}
