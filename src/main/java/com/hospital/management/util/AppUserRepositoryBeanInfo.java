/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hospital.management.util;

import java.beans.*;

/**
 *
 * @author Zrael
 */
public class AppUserRepositoryBeanInfo extends SimpleBeanInfo {

    // Bean descriptor//GEN-FIRST:BeanDescriptor
    /*lazy BeanDescriptor*/
    private static BeanDescriptor getBdescriptor(){
        BeanDescriptor beanDescriptor = new BeanDescriptor  ( com.hospital.management.repository.PatientRepository.class , null ); // NOI18N//GEN-HEADEREND:BeanDescriptor
        // Here you can add code for customizing the BeanDescriptor.

        return beanDescriptor;     }//GEN-LAST:BeanDescriptor


    // Property identifiers//GEN-FIRST:Properties

    // Property array 
    /*lazy PropertyDescriptor*/
    private static PropertyDescriptor[] getPdescriptor(){
        PropertyDescriptor[] properties = new PropertyDescriptor[0];//GEN-HEADEREND:Properties
        // Here you can add code for customizing the properties array.

        return properties;     }//GEN-LAST:Properties

    // EventSet identifiers//GEN-FIRST:Events

    // EventSet array
    /*lazy EventSetDescriptor*/
    private static EventSetDescriptor[] getEdescriptor(){
        EventSetDescriptor[] eventSets = new EventSetDescriptor[0];//GEN-HEADEREND:Events
        // Here you can add code for customizing the event sets array.

        return eventSets;     }//GEN-LAST:Events

    // Method identifiers//GEN-FIRST:Methods
    private static final int METHOD_count0 = 0;
    private static final int METHOD_count1 = 1;
    private static final int METHOD_delete2 = 2;
    private static final int METHOD_deleteAll3 = 3;
    private static final int METHOD_deleteAll4 = 4;
    private static final int METHOD_deleteAllInBatch5 = 5;
    private static final int METHOD_deleteById6 = 6;
    private static final int METHOD_deleteInBatch7 = 7;
    private static final int METHOD_exists8 = 8;
    private static final int METHOD_existsById9 = 9;
    private static final int METHOD_findAll10 = 10;
    private static final int METHOD_findAll11 = 11;
    private static final int METHOD_findAll12 = 12;
    private static final int METHOD_findAll13 = 13;
    private static final int METHOD_findAll14 = 14;
    private static final int METHOD_findAll15 = 15;
    private static final int METHOD_findAllById16 = 16;
    private static final int METHOD_findByEmail17 = 17;
    private static final int METHOD_findById18 = 18;
    private static final int METHOD_findOne19 = 19;
    private static final int METHOD_flush20 = 20;
    private static final int METHOD_getOne21 = 21;
    private static final int METHOD_save22 = 22;
    private static final int METHOD_saveAll23 = 23;
    private static final int METHOD_saveAndFlush24 = 24;

    // Method array 
    /*lazy MethodDescriptor*/
    private static MethodDescriptor[] getMdescriptor(){
        MethodDescriptor[] methods = new MethodDescriptor[25];
    
        try {
            methods[METHOD_count0] = new MethodDescriptor(org.springframework.data.repository.query.QueryByExampleExecutor.class.getMethod("count", new Class[] {org.springframework.data.domain.Example.class})); // NOI18N
            methods[METHOD_count0].setDisplayName ( "" );
            methods[METHOD_count1] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("count", new Class[] {})); // NOI18N
            methods[METHOD_count1].setDisplayName ( "" );
            methods[METHOD_delete2] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("delete", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_delete2].setDisplayName ( "" );
            methods[METHOD_deleteAll3] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("deleteAll", new Class[] {java.lang.Iterable.class})); // NOI18N
            methods[METHOD_deleteAll3].setDisplayName ( "" );
            methods[METHOD_deleteAll4] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("deleteAll", new Class[] {})); // NOI18N
            methods[METHOD_deleteAll4].setDisplayName ( "" );
            methods[METHOD_deleteAllInBatch5] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("deleteAllInBatch", new Class[] {})); // NOI18N
            methods[METHOD_deleteAllInBatch5].setDisplayName ( "" );
            methods[METHOD_deleteById6] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("deleteById", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_deleteById6].setDisplayName ( "" );
            methods[METHOD_deleteInBatch7] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("deleteInBatch", new Class[] {java.lang.Iterable.class})); // NOI18N
            methods[METHOD_deleteInBatch7].setDisplayName ( "" );
            methods[METHOD_exists8] = new MethodDescriptor(org.springframework.data.repository.query.QueryByExampleExecutor.class.getMethod("exists", new Class[] {org.springframework.data.domain.Example.class})); // NOI18N
            methods[METHOD_exists8].setDisplayName ( "" );
            methods[METHOD_existsById9] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("existsById", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_existsById9].setDisplayName ( "" );
            methods[METHOD_findAll10] = new MethodDescriptor(org.springframework.data.repository.query.QueryByExampleExecutor.class.getMethod("findAll", new Class[] {org.springframework.data.domain.Example.class, org.springframework.data.domain.Pageable.class})); // NOI18N
            methods[METHOD_findAll10].setDisplayName ( "" );
            methods[METHOD_findAll11] = new MethodDescriptor(org.springframework.data.repository.PagingAndSortingRepository.class.getMethod("findAll", new Class[] {org.springframework.data.domain.Pageable.class})); // NOI18N
            methods[METHOD_findAll11].setDisplayName ( "" );
            methods[METHOD_findAll12] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("findAll", new Class[] {})); // NOI18N
            methods[METHOD_findAll12].setDisplayName ( "" );
            methods[METHOD_findAll13] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("findAll", new Class[] {org.springframework.data.domain.Sort.class})); // NOI18N
            methods[METHOD_findAll13].setDisplayName ( "" );
            methods[METHOD_findAll14] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("findAll", new Class[] {org.springframework.data.domain.Example.class})); // NOI18N
            methods[METHOD_findAll14].setDisplayName ( "" );
            methods[METHOD_findAll15] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("findAll", new Class[] {org.springframework.data.domain.Example.class, org.springframework.data.domain.Sort.class})); // NOI18N
            methods[METHOD_findAll15].setDisplayName ( "" );
            methods[METHOD_findAllById16] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("findAllById", new Class[] {java.lang.Iterable.class})); // NOI18N
            methods[METHOD_findAllById16].setDisplayName ( "" );
            methods[METHOD_findByEmail17] = new MethodDescriptor(com.hospital.management.repository.PatientRepository.class.getMethod("findByEmail", new Class[] {java.lang.String.class})); // NOI18N
            methods[METHOD_findByEmail17].setDisplayName ( "" );
            methods[METHOD_findById18] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("findById", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_findById18].setDisplayName ( "" );
            methods[METHOD_findOne19] = new MethodDescriptor(org.springframework.data.repository.query.QueryByExampleExecutor.class.getMethod("findOne", new Class[] {org.springframework.data.domain.Example.class})); // NOI18N
            methods[METHOD_findOne19].setDisplayName ( "" );
            methods[METHOD_flush20] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("flush", new Class[] {})); // NOI18N
            methods[METHOD_flush20].setDisplayName ( "" );
            methods[METHOD_getOne21] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("getOne", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_getOne21].setDisplayName ( "" );
            methods[METHOD_save22] = new MethodDescriptor(org.springframework.data.repository.CrudRepository.class.getMethod("save", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_save22].setDisplayName ( "" );
            methods[METHOD_saveAll23] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("saveAll", new Class[] {java.lang.Iterable.class})); // NOI18N
            methods[METHOD_saveAll23].setDisplayName ( "" );
            methods[METHOD_saveAndFlush24] = new MethodDescriptor(org.springframework.data.jpa.repository.JpaRepository.class.getMethod("saveAndFlush", new Class[] {java.lang.Object.class})); // NOI18N
            methods[METHOD_saveAndFlush24].setDisplayName ( "" );
        }
        catch( Exception e) {}//GEN-HEADEREND:Methods
        // Here you can add code for customizing the methods array.

        return methods;     }//GEN-LAST:Methods

    private static java.awt.Image iconColor16 = null;//GEN-BEGIN:IconsDef
    private static java.awt.Image iconColor32 = null;
    private static java.awt.Image iconMono16 = null;
    private static java.awt.Image iconMono32 = null;//GEN-END:IconsDef
    private static String iconNameC16 = null;//GEN-BEGIN:Icons
    private static String iconNameC32 = null;
    private static String iconNameM16 = null;
    private static String iconNameM32 = null;//GEN-END:Icons

    private static final int defaultPropertyIndex = -1;//GEN-BEGIN:Idx
    private static final int defaultEventIndex = -1;//GEN-END:Idx


//GEN-FIRST:Superclass
    // Here you can add code for customizing the Superclass BeanInfo.

//GEN-LAST:Superclass
    /**
     * Gets the bean's <code>BeanDescriptor</code>s.
     *
     * @return BeanDescriptor describing the editable properties of this bean.
     * May return null if the information should be obtained by automatic
     * analysis.
     */
    @Override
    public BeanDescriptor getBeanDescriptor() {
        return getBdescriptor();
    }

    /**
     * Gets the bean's <code>PropertyDescriptor</code>s.
     *
     * @return An array of PropertyDescriptors describing the editable
     * properties supported by this bean. May return null if the information
     * should be obtained by automatic analysis.
     * <p>
     * If a property is indexed, then its entry in the result array will belong
     * to the IndexedPropertyDescriptor subclass of PropertyDescriptor. A client
     * of getPropertyDescriptors can use "instanceof" to check if a given
     * PropertyDescriptor is an IndexedPropertyDescriptor.
     */
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return getPdescriptor();
    }

    /**
     * Gets the bean's <code>EventSetDescriptor</code>s.
     *
     * @return An array of EventSetDescriptors describing the kinds of events
     * fired by this bean. May return null if the information should be obtained
     * by automatic analysis.
     */
    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        return getEdescriptor();
    }

    /**
     * Gets the bean's <code>MethodDescriptor</code>s.
     *
     * @return An array of MethodDescriptors describing the methods implemented
     * by this bean. May return null if the information should be obtained by
     * automatic analysis.
     */
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        return getMdescriptor();
    }

    /**
     * A bean may have a "default" property that is the property that will
     * mostly commonly be initially chosen for update by human's who are
     * customizing the bean.
     *
     * @return Index of default property in the PropertyDescriptor array
     * returned by getPropertyDescriptors.
     * <P>
     * Returns -1 if there is no default property.
     */
    @Override
    public int getDefaultPropertyIndex() {
        return defaultPropertyIndex;
    }

    /**
     * A bean may have a "default" event that is the event that will mostly
     * commonly be used by human's when using the bean.
     *
     * @return Index of default event in the EventSetDescriptor array returned
     * by getEventSetDescriptors.
     * <P>
     * Returns -1 if there is no default event.
     */
    @Override
    public int getDefaultEventIndex() {
        return defaultEventIndex;
    }

    /**
     * This method returns an image object that can be used to represent the
     * bean in toolboxes, toolbars, etc. Icon images will typically be GIFs, but
     * may in future include other formats.
     * <p>
     * Beans aren't required to provide icons and may return null from this
     * method.
     * <p>
     * There are four possible flavors of icons (16x16 color, 32x32 color, 16x16
     * mono, 32x32 mono). If a bean choses to only support a single icon we
     * recommend supporting 16x16 color.
     * <p>
     * We recommend that icons have a "transparent" background so they can be
     * rendered onto an existing background.
     *
     * @param iconKind The kind of icon requested. This should be one of the
     * constant values ICON_COLOR_16x16, ICON_COLOR_32x32, ICON_MONO_16x16, or
     * ICON_MONO_32x32.
     * @return An image object representing the requested icon. May return null
     * if no suitable icon is available.
     */
    @Override
    public java.awt.Image getIcon(int iconKind) {
        switch (iconKind) {
            case ICON_COLOR_16x16:
                if (iconNameC16 == null) {
                    return null;
                } else {
                    if (iconColor16 == null) {
                        iconColor16 = loadImage(iconNameC16);
                    }
                    return iconColor16;
                }
            case ICON_COLOR_32x32:
                if (iconNameC32 == null) {
                    return null;
                } else {
                    if (iconColor32 == null) {
                        iconColor32 = loadImage(iconNameC32);
                    }
                    return iconColor32;
                }
            case ICON_MONO_16x16:
                if (iconNameM16 == null) {
                    return null;
                } else {
                    if (iconMono16 == null) {
                        iconMono16 = loadImage(iconNameM16);
                    }
                    return iconMono16;
                }
            case ICON_MONO_32x32:
                if (iconNameM32 == null) {
                    return null;
                } else {
                    if (iconMono32 == null) {
                        iconMono32 = loadImage(iconNameM32);
                    }
                    return iconMono32;
                }
            default:
                return null;
        }
    }
    
}
