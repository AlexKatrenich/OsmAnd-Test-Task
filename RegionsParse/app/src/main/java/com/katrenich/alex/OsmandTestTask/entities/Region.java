package com.katrenich.alex.OsmandTestTask.entities;

import android.support.annotation.Nullable;

import java.util.List;
import java.util.Objects;

public class Region {
    private String name;
    private String downloadName;

    /*Flags*/
    private Flag map;
    private Flag srtm;
    private Flag hillshade;
    private Flag roads;
    private Flag wiki;
    private Flag joinMapFiles;
    private String boundary;

    @Nullable private String type;
    @Nullable private String translate;
    @Nullable private String lang;
    @Nullable private String mPolyExtract;
    @Nullable private String downLoadPrefix;
    @Nullable private String downLoadSuffix;
    @Nullable private String innerDownLoadSuffix;

    @Nullable private Region parentRegion;

    public Region() {
        this.map = Flag.YES;
        this.srtm = Flag.YES;
        this.hillshade = Flag.YES;
        this.roads = Flag.YES;
        this.wiki = Flag.YES;
        this.joinMapFiles = Flag.NO;
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public String getDownloadName() {
        return downloadName;
    }

    public Flag getMap() {
        return map;
    }

    public Flag getSrtm() {
        return srtm;
    }

    public Flag getHillshade() {
        return hillshade;
    }

    public Flag getRoads() {
        return roads;
    }

    public Flag getWiki() {
        return wiki;
    }

    public Flag getJoinMapFiles() {
        return joinMapFiles;
    }

    public String getType() {
        return type;
    }

    public String getBoundary() {
        return boundary;
    }

    public String getLang() {
        return lang;
    }

    public String getTranslate() {
        return translate;
    }

    public String getPolyExtract() {
        return mPolyExtract;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDownloadName(String downloadName) {
        this.downloadName = downloadName;
    }

    public void setMap(Flag map) {
        this.map = map;
    }

    public void setSrtm(Flag srtm) {
        this.srtm = srtm;
    }

    public void setHillshade(Flag hillshade) {
        this.hillshade = hillshade;
    }

    public void setRoads(Flag roads) {
        this.roads = roads;
    }

    public void setWiki(Flag wiki) {
        this.wiki = wiki;
    }

    public void setJoinMapFiles(Flag joinMapFiles) {
        this.joinMapFiles = joinMapFiles;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBoundary(String boundary) {
        this.boundary = boundary;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public void setPolyExtract(String polyExtract) {
        this.mPolyExtract = polyExtract;
    }

    @Nullable
    public String getDownLoadPrefix() {
        return downLoadPrefix;
    }

    public void setDownLoadPrefix(@Nullable String downLoadPrefix) {
        this.downLoadPrefix = downLoadPrefix;
    }

    @Nullable
    public String getDownLoadSuffix() {
        return downLoadSuffix;
    }

    public void setDownLoadSuffix(@Nullable String downLoadSuffix) {
        this.downLoadSuffix = downLoadSuffix;
    }

    @Nullable
    public String getInnerDownLoadSuffix() {
        return innerDownLoadSuffix;
    }

    public void setInnerDownLoadSuffix(@Nullable String innerDownLoadSuffix) {
        this.innerDownLoadSuffix = innerDownLoadSuffix;
    }

    @Nullable
    public Region getParentRegion() {
        return parentRegion;
    }

    public void setParentRegion(@Nullable Region parentRegion) {
        this.parentRegion = parentRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return name.equals(region.name) &&
                downloadName.equals(region.downloadName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, downloadName);
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                ", downloadName='" + downloadName + '\'' +
                ", map=" + map +
                ", srtm=" + srtm +
                ", hillshade=" + hillshade +
                ", roads=" + roads +
                ", wiki=" + wiki +
                ", parent=" + parentRegion +
                ", joinMapFiles=" + joinMapFiles +
                '}';
    }

}
