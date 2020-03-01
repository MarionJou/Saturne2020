/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.pgrou.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mario
 */
@Repository
public class ImageCustomRepositoryImpl implements ImageCustomRepository{
    @Autowired
    @Lazy
    ImageRepository imageRepository;
    
}
